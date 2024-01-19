package com.github.osmundf.mongo.board.service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osmundf.mongo.board.model.request.InsertManyRequest;
import com.github.osmundf.mongo.board.model.request.InsertOneRequest;
import com.github.osmundf.mongo.board.view.result.InsertManyResultModel;
import com.github.osmundf.mongo.board.view.result.InsertOneResultModel;
import com.mongodb.MongoException;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.InsertOneOptions;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Insert Service.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@Service
public class InsertService {

  private final MongoService mongoService;

  private final DocumentService documentService;

  /**
   * Constructor for Insert Service.
   *
   * @param mongoService Mongo service
   * @param documentService document service
   */
  @Autowired
  public InsertService(MongoService mongoService, DocumentService documentService) {
    this.mongoService = mongoService;
    this.documentService = documentService;
  }

  /**
   * Perform insert one operation.
   *
   * @param request insert one request
   * @see com.github.osmundf.mongo.board.model.request.InsertOneRequest
   * @return insert one result
   */
  @NonNull
  public InsertOneResultModel insertOne(@NonNull InsertOneRequest request) {
    try {
      final var document = documentService.toDocument("document", request.getDocument());
      if (document == null) {
        final var message = "Document field \"document\" is missing.";
        throw new ResponseStatusException(BAD_REQUEST, message);
      }
      final var collection = mongoService.getCollection(request);
      final var options =
          new InsertOneOptions().bypassDocumentValidation(request.bypassDocumentValidation());
      final var result = collection.insertOne(document, options);
      return convert(result);
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  /**
   * Perform insert many operation.
   *
   * @param request insert many request
   * @see com.github.osmundf.mongo.board.model.request.InsertManyRequest
   * @return insert many result
   */
  @NonNull
  public InsertManyResultModel insertMany(@NonNull InsertManyRequest request) {
    try {
      final var documents = documentService.toDocuments("documents", request.getDocuments());
      if (documents == null) {
        final var message = "Documents field \"documents\" is missing.";
        throw new ResponseStatusException(BAD_REQUEST, message);
      }
      if (documentService.containsNulls(documents)) {
        final var message = "Documents field \"documents\" contains null(s).";
        throw new ResponseStatusException(BAD_REQUEST, message);
      }
      final var collection = mongoService.getCollection(request);
      final var options =
          new InsertManyOptions()
              .bypassDocumentValidation(request.bypassDocumentValidation())
              .ordered(request.isOrdered());
      final var result = collection.insertMany(documents, options);
      return convert(result);
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  @NonNull
  private InsertOneResultModel convert(@NonNull InsertOneResult input) {
    final var result = new InsertOneResultModel();
    result.setId(documentService.toJsonNode(input.getInsertedId()));
    result.setAcknowledged(input.wasAcknowledged());
    return result;
  }

  @NonNull
  private InsertManyResultModel convert(@NonNull InsertManyResult input) {
    final var result = new InsertManyResultModel();
    final var map = new LinkedHashMap<Integer, JsonNode>(input.getInsertedIds().size());
    for (final var entry : input.getInsertedIds().entrySet()) {
      map.put(entry.getKey(), documentService.toJsonNode(entry.getValue()));
    }
    result.setIds(map);
    result.setAcknowledged(input.wasAcknowledged());
    return result;
  }
}
