package com.github.osmundf.mongo.board.service;

import com.github.osmundf.mongo.board.configuration.DocumentBoardProperties;
import com.github.osmundf.mongo.board.model.request.CollectionRequest;
import com.github.osmundf.mongo.board.model.request.DatabaseRequest;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Mongo Service.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@Service
public class MongoService {

  private final MongoClient mongoClient;

  private final EnumService enumService;

  /**
   * Constructor for Mongo Service.
   *
   * @param properties document board properties
   * @param enumService enum service
   */
  @Autowired
  public MongoService(final DocumentBoardProperties properties, EnumService enumService) {
    mongoClient = MongoClients.create(connectionString(properties));
    this.enumService = enumService;
  }

  /**
   * Return Mongo database for database request.
   *
   * @param request database request
   * @see com.github.osmundf.mongo.board.model.request.DatabaseRequest
   * @return Mongo database object
   */
  @NonNull
  public MongoDatabase getDatabase(@Nullable DatabaseRequest request) {

    if (request == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty request.");
    }

    if (request.getDatabase() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing database name.");
    }

    if (request.getDatabase().isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database name is blank.");
    }

    if (request.getReadPreference() == null) {
      if (request.getTagList() != null) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, "Tag list defined without read preference.");
      }
      if (request.getMaxStaleness() != null) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, "Max staleness defined without read preference.");
      }
    }

    var database = mongoClient.getDatabase(request.getDatabase());

    if (request.getReadPreference() != null) {
      final var readPreference =
          enumService.getReadPreference(
              request.getReadPreference(), request.getTagList(), request.getMaxStaleness());
      database = database.withReadPreference(readPreference);
    }

    if (request.getReadConcern() != null) {
      database = database.withReadConcern(enumService.getReadConcern(request.getReadConcern()));
    }

    if (request.getWriteConcern() != null) {
      database = database.withWriteConcern(enumService.getWriteConcern(request.getWriteConcern()));
    }

    return database;
  }

  /**
   * Return Mongo collection for collection request.
   *
   * @param request collection request
   * @see com.github.osmundf.mongo.board.model.request.CollectionRequest
   * @return Mongo collection object
   */
  @NonNull
  public MongoCollection<Document> getCollection(@Nullable CollectionRequest request) {

    if (request == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty request.");
    }

    if (request.getCollection() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing collection name.");
    }

    if (request.getCollection().isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Collection name is blank.");
    }

    final var database = getDatabase(request);

    return database.getCollection(request.getCollection());
  }

  private String connectionString(DocumentBoardProperties properties) {
    return String.format(
        "%s://%s:%s@%s/%s",
        properties.hostType(),
        properties.username(),
        new String(properties.password()),
        properties.host(),
        properties.options());
  }
}
