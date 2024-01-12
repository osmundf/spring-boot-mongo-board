package com.github.osmundf.mongo.board.service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.bulk.BulkWriteRequest;
import com.github.osmundf.mongo.board.model.data.UpdateOrPipeline;
import com.github.osmundf.mongo.board.view.result.BulkWriteResultModel;
import com.mongodb.Function;
import com.mongodb.MongoException;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.DeleteManyModel;
import com.mongodb.client.model.DeleteOneModel;
import com.mongodb.client.model.DeleteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.UpdateManyModel;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.WriteModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.bson.BSONException;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BulkWriteService {

  private final MongoService mongoService;

  private final DocumentService documentService;

  private final BulkDocumentService bulkDocumentService;

  private final PipelineService pipelineService;

  private final CollationService collationService;

  @Autowired
  public BulkWriteService(
      MongoService mongoService,
      DocumentService documentService,
      BulkDocumentService bulkDocumentService,
      PipelineService pipelineService,
      CollationService collationService) {
    this.mongoService = mongoService;
    this.documentService = documentService;
    this.bulkDocumentService = bulkDocumentService;
    this.pipelineService = pipelineService;
    this.collationService = collationService;
  }

  public BulkWriteResultModel bulkWrite(BulkWriteRequest request) {
    try {
      final var collection = mongoService.getCollection(request);
      final var requests = toWriteModels(request.getRequests());
      final var options =
          new BulkWriteOptions()
              .let(documentService.toBson("let", request.getLet()))
              .bypassDocumentValidation(request.bypassDocumentValidation())
              .ordered(request.isOrdered());
      final var result = collection.bulkWrite(requests, options);
      return convert(result);
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  private List<WriteModel<Document>> toWriteModels(@Nullable List<Map<String, ObjectNode>> input) {

    // Check for null input.
    if (input == null) {
      final var message = "Missing \"requests\" document.";
      throw new ResponseStatusException(BAD_REQUEST, message);
    }

    // Check for empty input.
    if (input.isEmpty()) {
      return Collections.emptyList();
    }

    final var size = input.size();
    final var list = new ArrayList<WriteModel<Document>>(size);

    for (int i = 0; i < size; i++) {
      final var map = input.get(i);
      if (map.size() != 1) {
        final var message =
            String.format(
                "Failed to retrieve single field from \"%s\" document map[%d].", "requests", i);
        throw new ResponseStatusException(BAD_REQUEST, message);
      }
      final var key = map.keySet().stream().findFirst().orElse(null);
      final var modelInput = map.get(key);

      try {
        final var model =
            switch (key) {
              case "insertOne" -> insertOneModel(modelInput);
              case "updateOne" -> updateOneModel(modelInput);
              case "updateMany" -> updateManyModel(modelInput);
              case "replaceOne" -> replaceOneModel(modelInput);
              case "deleteOne" -> deleteOneModel(modelInput);
              case "deleteMany" -> deleteManyModel(modelInput);
              default -> {
                final var message =
                    String.format(
                        "Failed to assign model from \"requests\" document map[%d] (%s).", i, key);
                throw new ResponseStatusException(BAD_REQUEST, message);
              }
            };
        list.add(model);
      }
      // Catch BSON and Mongo exceptions.
      catch (BSONException | MongoException e) {
        final var message =
            String.format("Failed \"%s\" document map[%d] (%s).", "requests", i, key);
        throw new ResponseStatusException(BAD_REQUEST, message, e);
      }
    }

    return list;
  }

  private InsertOneModel<Document> insertOneModel(@NonNull ObjectNode input) {
    // new InsertOneModel<Document>(new Document());
    final var model = bulkDocumentService.getInsert(input);
    final var document = documentService.toBson("document", model.getDocument());
    if (document == null) {
      final var message = "Insert one \"document\" missing.";
      throw new ResponseStatusException(BAD_REQUEST, message);
    }
    return new InsertOneModel<>(documentService.toDocument(document));
  }

  private UpdateOneModel<Document> updateOneModel(@NonNull ObjectNode input) {
    return updateModel(
        "Update one",
        input,
        parameters ->
            switch (parameters.updateOrPipeline.process()) {
              case UPDATE_WITH_DOCUMENT -> new UpdateOneModel<>(
                  parameters.filter, parameters.updateOrPipeline.document(), parameters.options);
              case UPDATE_WITH_PIPELINE -> new UpdateOneModel<>(
                  parameters.filter, parameters.updateOrPipeline.pipeline(), parameters.options);
            });
  }

  private UpdateManyModel<Document> updateManyModel(@NonNull ObjectNode input) {
    return updateModel(
        "Update many",
        input,
        parameters ->
            switch (parameters.updateOrPipeline.process()) {
              case UPDATE_WITH_DOCUMENT -> new UpdateManyModel<>(
                  parameters.filter, parameters.updateOrPipeline.document(), parameters.options);
              case UPDATE_WITH_PIPELINE -> new UpdateManyModel<>(
                  parameters.filter, parameters.updateOrPipeline.pipeline(), parameters.options);
            });
  }

  private <T extends WriteModel<Document>> T updateModel(
      String operation, ObjectNode input, Function<UpdateFunctionParameters, T> function) {
    final var model = bulkDocumentService.getUpdate(input);
    final var filter = documentService.toBson("filter", model.getFilter());
    if (filter == null) {
      final var message = String.format("%s \"filter\" missing.", operation);
      throw new ResponseStatusException(BAD_REQUEST, message);
    }
    final var updateOrPipeline =
        pipelineService.getUpdateOrPipeline(operation, model.getUpdate(), model.getPipeline());
    final var let = documentService.toBson("let", model.getLet());
    final var updateOptions =
        new UpdateOptions()
            .arrayFilters(documentService.toBsons("arrayFilters", model.getArrayFilters()))
            .upsert(model.isUpsert())
            .bypassDocumentValidation(model.bypassDocumentValidation())
            .hint(documentService.toBson("hint", model.getHint()))
            .collation(collationService.toCollation(model.getCollation()));
    if (let != null) {
      updateOptions.let(let);
    }
    return function.apply(new UpdateFunctionParameters(filter, updateOrPipeline, updateOptions));
  }

  private ReplaceOneModel<Document> replaceOneModel(@NonNull ObjectNode input) {
    final var model = bulkDocumentService.getReplace(input);
    final var filter = documentService.toBson("filter", model.getFilter());
    if (filter == null) {
      final var message = "Replace one \"filter\" missing.";
      throw new ResponseStatusException(BAD_REQUEST, message);
    }
    final var document = documentService.toBson("document", model.getDocument());
    if (document == null) {
      final var message = "Replace one \"document\" missing.";
      throw new ResponseStatusException(BAD_REQUEST, message);
    }
    final var let = documentService.toBson("let", model.getLet());
    final var hint = documentService.toBson("hint", model.getHint());
    final var collation = collationService.toCollation(model.getCollation());
    final var replaceOptions =
        new ReplaceOptions()
            .upsert(model.isUpsert())
            .bypassDocumentValidation(model.bypassDocumentValidation())
            .hint(hint)
            .collation(collation);
    if (let != null) {
      replaceOptions.let(let);
    }
    return new ReplaceOneModel<>(filter, documentService.toDocument(document), replaceOptions);
  }

  private DeleteOneModel<Document> deleteOneModel(@NonNull ObjectNode input) {
    return deleteModel(
        input, parameters -> new DeleteOneModel<>(parameters.filter, parameters.options));
  }

  private DeleteManyModel<Document> deleteManyModel(@NonNull ObjectNode input) {
    return deleteModel(
        input, parameters -> new DeleteManyModel<>(parameters.filter, parameters.options));
  }

  private <T extends WriteModel<Document>> T deleteModel(
      ObjectNode input, Function<DeleteFunctionParameters, T> function) {
    final var model = bulkDocumentService.getDelete(input);
    final var filter = documentService.toBson("filter", model.getFilter());
    if (filter == null) {
      final var message = "Bulk delete \"filter\" missing.";
      throw new ResponseStatusException(BAD_REQUEST, message);
    }
    final var let = documentService.toBson("let", model.getLet());
    final var hint = documentService.toBson("hint", model.getHint());
    final var collation = collationService.toCollation(model.getCollation());
    final var deleteOptions = new DeleteOptions().hint(hint).collation(collation);
    if (let != null) {
      deleteOptions.let(let);
    }
    return function.apply(new DeleteFunctionParameters(filter, deleteOptions));
  }

  private BulkWriteResultModel convert(@NonNull BulkWriteResult input) {
    final var result = new BulkWriteResultModel();
    final var insertsMap = new LinkedHashMap<Integer, JsonNode>(input.getInserts().size());
    for (final var entry : input.getInserts()) {
      insertsMap.put(entry.getIndex(), documentService.toJsonNode(entry.getId()));
    }
    final var upsertsMap = new LinkedHashMap<Integer, JsonNode>(input.getUpserts().size());
    for (final var entry : input.getUpserts()) {
      upsertsMap.put(entry.getIndex(), documentService.toJsonNode(entry.getId()));
    }
    result.setInserts(insertsMap);
    result.setUpserts(upsertsMap);
    result.setMatchedCount(input.getMatchedCount());
    result.setInsertedCount(input.getInsertedCount());
    result.setModifiedCount(input.getModifiedCount());
    result.setDeletedCount(input.getDeletedCount());
    return result;
  }

  private record UpdateFunctionParameters(
      @NonNull Bson filter,
      @NonNull UpdateOrPipeline updateOrPipeline,
      @NonNull UpdateOptions options) {}

  private record DeleteFunctionParameters(@NonNull Bson filter, @NonNull DeleteOptions options) {}
}
