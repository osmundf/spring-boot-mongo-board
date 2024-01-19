package com.github.osmundf.mongo.board.service;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.CollectionRequest;
import com.github.osmundf.mongo.board.model.request.CreateIndexRequest;
import com.github.osmundf.mongo.board.model.request.DropIndexRequest;
import com.mongodb.MongoException;
import com.mongodb.client.model.DropIndexOptions;
import com.mongodb.client.model.IndexOptions;
import java.util.List;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Index Service.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@Service
public class IndexService {

  private final MongoService mongoService;

  private final DocumentService documentService;

  private final CollationService collationService;

  /**
   * Constructor for Index Service.
   *
   * @param mongoService Mongo service
   * @param documentService document service
   * @param collationService collation service
   */
  @Autowired
  public IndexService(
      MongoService mongoService,
      DocumentService documentService,
      CollationService collationService) {
    this.mongoService = mongoService;
    this.documentService = documentService;
    this.collationService = collationService;
  }

  /**
   * Perform list indexes operation.
   *
   * @param request collection request
   * @see com.github.osmundf.mongo.board.model.request.CollectionRequest
   * @return a list of indexes, an empty list otherwise
   */
  @NonNull
  public List<ObjectNode> listIndexes(@NonNull CollectionRequest request) {

    try {
      final var collection = mongoService.getCollection(request);
      return StreamSupport.stream(collection.listIndexes().spliterator(), false)
          .map(documentService::toObjectNode)
          .toList();
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  /**
   * Perform create index operation.
   *
   * @param request create index request
   * @see com.github.osmundf.mongo.board.model.request.CreateIndexRequest
   * @return create index result
   */
  @NonNull
  public String createIndex(@NonNull CreateIndexRequest request) {
    try {
      final var collection = mongoService.getCollection(request);
      final var keys = documentService.toBson("keys", request.getKeys());
      if (keys == null) {
        final var message = "Create index \"keys\" are missing.";
        throw new ResponseStatusException(BAD_REQUEST, message);
      }
      final var options = new IndexOptions();
      options.defaultLanguage(request.getDefaultLanguage());
      options.bits(request.getBits());
      options.background(request.isBackground());
      options.expireAfter(request.getExpireAfter(), SECONDS);
      options.collation(collationService.toCollation(request.getCollation()));
      return collection.createIndex(keys, options);
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  /**
   * Perform drop index operation.
   *
   * @param request drop index request
   * @return void
   */
  @Nullable
  public Void dropIndex(@NonNull DropIndexRequest request) {
    try {
      if (request.getName() == null) {
        throw new ResponseStatusException(BAD_REQUEST, "Index name is null.");
      }
      final var collection = mongoService.getCollection(request);
      final var options = new DropIndexOptions();
      if (request.getMaxTime() != null) {
        options.maxTime(request.getMaxTime(), MILLISECONDS);
      }
      collection.dropIndex(request.getName(), options);
      return null;
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }
}
