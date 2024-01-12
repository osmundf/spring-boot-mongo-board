package com.github.osmundf.mongo.board.service;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.FindOneAndUpdateRequest;
import com.github.osmundf.mongo.board.model.request.UpdateRequest;
import com.mongodb.Function;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateService {

  private final MongoService mongoService;

  private final EnumService enumService;

  private final DocumentService documentService;

  private final PipelineService pipelineService;

  private final CollationService collationService;

  @Autowired
  public UpdateService(
      MongoService mongoService,
      EnumService enumService,
      DocumentService documentService,
      PipelineService pipelineService,
      CollationService collationService) {
    this.mongoService = mongoService;
    this.enumService = enumService;
    this.documentService = documentService;
    this.pipelineService = pipelineService;
    this.collationService = collationService;
  }

  @Nullable
  public ObjectNode findOneAndUpdate(@NonNull FindOneAndUpdateRequest request) {
    try {
      final var returnDocument = enumService.getReturnDocument(request.getReturnUpdate());
      final var collection = mongoService.getCollection(request);
      final var filter = documentService.toBson("filter", request.getFilter());
      if (filter == null) {
        final var message = "Find one and update \"filter\" is missing.";
        throw new ResponseStatusException(BAD_REQUEST, message);
      }
      final var updateOrPipeline =
          pipelineService.getUpdateOrPipeline(
              "Find one and update", request.getUpdate(), request.getPipeline());
      final var options =
          new FindOneAndUpdateOptions()
              .projection(documentService.toBson("projection", request.getProjection()))
              .sort(documentService.toBson("sort", request.getSort()))
              .arrayFilters(documentService.toBsons("arrayFilters", request.getArrayFilters()))
              .upsert(request.isUpsert())
              .returnDocument(returnDocument)
              .hint(documentService.toBson("hint", request.getHint()))
              .bypassDocumentValidation(request.bypassDocumentValidation())
              .collation(collationService.toCollation(request.getCollation()));
      if (request.getLet() != null) {
        options.let(documentService.toBson("let", request.getLet(), new BsonDocument()));
      }
      if (request.getMaxTime() != null) {
        options.maxTime(request.getMaxTime(), MILLISECONDS);
      }
      final var document =
          switch (updateOrPipeline.process()) {
            case UPDATE_WITH_DOCUMENT -> collection.findOneAndUpdate(
                filter, updateOrPipeline.document(), options);
            case UPDATE_WITH_PIPELINE -> collection.findOneAndUpdate(
                filter, updateOrPipeline.pipeline(), options);
          };
      return documentService.toObjectNode(document);
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  @NonNull
  public UpdateResult updateOne(@NonNull UpdateRequest request) {
    final var updateOrPipeline =
        pipelineService.getUpdateOrPipeline(
            "Update one", request.getUpdate(), request.getPipeline());
    return switch (updateOrPipeline.process()) {
      case UPDATE_WITH_DOCUMENT -> {
        final var function =
            (Function<UpdateParameters, UpdateResult>)
                (parameters) ->
                    parameters.collection.updateOne(
                        parameters.filter, updateOrPipeline.document(), parameters.options);
        yield update(request, "one", function);
      }
      case UPDATE_WITH_PIPELINE -> {
        final var function =
            (Function<UpdateParameters, UpdateResult>)
                (parameters) ->
                    parameters.collection.updateOne(
                        parameters.filter, updateOrPipeline.pipeline(), parameters.options);
        yield update(request, "one", function);
      }
    };
  }

  @NonNull
  public UpdateResult updateMany(@NonNull UpdateRequest request) {
    final var updateOrPipeline =
        pipelineService.getUpdateOrPipeline(
            "Update many", request.getUpdate(), request.getPipeline());
    return switch (updateOrPipeline.process()) {
      case UPDATE_WITH_DOCUMENT -> {
        final var function =
            (Function<UpdateParameters, UpdateResult>)
                (parameters) ->
                    parameters.collection.updateMany(
                        parameters.filter, updateOrPipeline.document(), parameters.options);
        yield update(request, "many", function);
      }
      case UPDATE_WITH_PIPELINE -> {
        final var function =
            (Function<UpdateParameters, UpdateResult>)
                (parameters) ->
                    parameters.collection.updateMany(
                        parameters.filter, updateOrPipeline.pipeline(), parameters.options);
        yield update(request, "many", function);
      }
    };
  }

  @NonNull
  private UpdateResult update(
      @NonNull UpdateRequest request,
      @NonNull String type,
      @NonNull Function<UpdateParameters, UpdateResult> function) {
    try {
      final var collection = mongoService.getCollection(request);
      final var filter = documentService.toBson("filter", request.getFilter());
      if (filter == null) {
        final var message = String.format("Update %s \"filter\" is missing.", type);
        throw new ResponseStatusException(BAD_REQUEST, message);
      }

      final var options =
          new UpdateOptions()
              .arrayFilters(documentService.toBsons("arrayFilters", request.getArrayFilters()))
              .upsert(request.isUpsert())
              .hint(documentService.toBson("hint", request.getHint()))
              .bypassDocumentValidation(request.bypassDocumentValidation())
              .collation(collationService.toCollation(request.getCollation()));
      if (request.getLet() != null) {
        options.let(documentService.toBson("let", request.getLet(), new BsonDocument()));
      }
      return function.apply(new UpdateParameters(collection, filter, options));
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  private record UpdateParameters(
      MongoCollection<Document> collection, Bson filter, UpdateOptions options) {}
}
