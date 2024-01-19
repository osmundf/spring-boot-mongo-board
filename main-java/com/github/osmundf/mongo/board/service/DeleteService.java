package com.github.osmundf.mongo.board.service;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.DeleteRequest;
import com.github.osmundf.mongo.board.model.request.FindOneAndDeleteRequest;
import com.github.osmundf.mongo.board.view.result.DeleteResultModel;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.DeleteOptions;
import com.mongodb.client.model.FindOneAndDeleteOptions;
import com.mongodb.client.result.DeleteResult;
import java.util.function.Function;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Delete Service.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@Service
public class DeleteService {

  private final MongoService mongoService;

  private final DocumentService documentService;

  private final CollationService collationService;

  /**
   * Constructor for Delete Service.
   *
   * @param mongoService Mongo service
   * @param documentService document service
   * @param collationService collation service
   */
  @Autowired
  public DeleteService(
      MongoService mongoService,
      DocumentService documentService,
      CollationService collationService) {
    this.mongoService = mongoService;
    this.documentService = documentService;
    this.collationService = collationService;
  }

  /**
   * Perform find one and delete operation.
   *
   * @param request find on and delete request
   * @see com.github.osmundf.mongo.board.model.request.FindOneAndDeleteRequest
   * @return find one and delete result, null otherwise
   */
  @Nullable
  public ObjectNode findOneAndDelete(@NonNull FindOneAndDeleteRequest request) {
    try {
      final var collection = mongoService.getCollection(request);
      final var filter = documentService.toBson("filter", request.getFilter());
      if (filter == null) {
        final var message = "Find one and delete \"filter\" is missing.";
        throw new ResponseStatusException(BAD_REQUEST, message);
      }
      final var options =
          new FindOneAndDeleteOptions()
              .projection(documentService.toBson("projection", request.getProjection()))
              .sort(documentService.toBson("sort", request.getSort()))
              .hint(documentService.toBson("hint", request.getHint()))
              .collation(collationService.toCollation(request.getCollation()));
      if (request.getLet() != null) {
        options.let(documentService.toBson("let", request.getLet(), new BsonDocument()));
      }
      if (request.getMaxTime() != null) {
        options.maxTime(request.getMaxTime(), MILLISECONDS);
      }
      final var document = collection.findOneAndDelete(filter, options);
      return documentService.toObjectNode(document);
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  /**
   * Perform delete one operation.
   *
   * @param request delete request
   * @see com.github.osmundf.mongo.board.model.request.DeleteRequest
   * @return delete result
   */
  public DeleteResultModel deleteOne(DeleteRequest request) {

    return delete(
        request,
        parameters -> parameters.collection.deleteOne(parameters.filter, parameters.options));
  }

  /**
   * Perform delete many operation.
   *
   * @param request delete request
   * @return delete result
   */
  public DeleteResultModel deleteMany(DeleteRequest request) {

    return delete(
        request,
        parameters -> parameters.collection.deleteMany(parameters.filter, parameters.options));
  }

  private DeleteResultModel delete(
      DeleteRequest request, Function<DeleteParameters, DeleteResult> function) {
    try {
      final var collection = mongoService.getCollection(request);
      final var filter = documentService.toBson("filter", request.getFilter());
      if (filter == null) {
        final var message = "Delete request \"filter\" is missing.";
        throw new ResponseStatusException(BAD_REQUEST, message);
      }
      final var options =
          new DeleteOptions()
              .hint(documentService.toBson("hint", request.getHint()))
              .collation(collationService.toCollation(request.getCollation()));
      if (request.getLet() != null) {
        options.let(documentService.toBson("let", request.getLet(), new BsonDocument()));
      }
      final var result = function.apply(new DeleteParameters(collection, filter, options));
      return convert(result);
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  private DeleteResultModel convert(@NonNull DeleteResult input) {
    final var result = new DeleteResultModel();
    result.setDeleteCount(input.getDeletedCount());
    result.setAcknowledged(input.wasAcknowledged());
    return result;
  }

  private record DeleteParameters(
      @NonNull MongoCollection<Document> collection,
      @NonNull Bson filter,
      @NonNull DeleteOptions options) {}
}
