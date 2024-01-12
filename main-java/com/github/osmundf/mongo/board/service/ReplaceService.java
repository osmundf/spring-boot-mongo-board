package com.github.osmundf.mongo.board.service;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.FindOneAndReplaceRequest;
import com.github.osmundf.mongo.board.model.request.ReplaceOneRequest;
import com.mongodb.MongoException;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonDocument;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReplaceService {

  private final MongoService mongoService;

  private final EnumService enumService;

  private final DocumentService documentService;

  private final CollationService collationService;

  @Autowired
  public ReplaceService(
      MongoService mongoService,
      EnumService enumService,
      DocumentService documentService,
      CollationService collationService) {
    this.mongoService = mongoService;
    this.enumService = enumService;
    this.documentService = documentService;
    this.collationService = collationService;
  }

  @Nullable
  public ObjectNode findOneAndReplace(@NonNull FindOneAndReplaceRequest request) {
    try {
      final var returnDocument = enumService.getReturnDocument(request.getReturnUpdate());
      final var collection = mongoService.getCollection(request);
      final var filter = documentService.toBson("filter", request.getFilter());
      if (filter == null) {
        final var message = "Find one and replace \"filter\" is missing.";
        throw new ResponseStatusException(BAD_REQUEST, message);
      }
      final var replacement = documentService.toDocument("document", request.getDocument());
      if (replacement == null) {
        final var message = "Find one and replace \"document\" is missing.";
        throw new ResponseStatusException(BAD_REQUEST, message);
      }
      final var options =
          new FindOneAndReplaceOptions()
              .projection(documentService.toBson("projection", request.getProjection()))
              .sort(documentService.toBson("sort", request.getSort()))
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
      final var document = collection.findOneAndReplace(filter, replacement, options);
      return documentService.toObjectNode(document);
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  @NonNull
  public UpdateResult replaceOne(@NonNull ReplaceOneRequest request) {
    try {
      final var collection = mongoService.getCollection(request);
      final var filter = documentService.toBson("filter", request.getFilter(), new BsonDocument());
      final var document =
          documentService.toDocument("document", request.getDocument(), new Document());
      final var options =
          new ReplaceOptions()
              .upsert(request.isUpsert())
              .hint(documentService.toBson("hint", request.getHint()))
              .bypassDocumentValidation(request.bypassDocumentValidation())
              .collation(collationService.toCollation(request.getCollation()));
      if (request.getLet() != null) {
        options.let(documentService.toBson("let", request.getLet(), new BsonDocument()));
      }

      return collection.replaceOne(filter, document, options);
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }
}
