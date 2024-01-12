package com.github.osmundf.mongo.board.service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.AggregateRequest;
import com.mongodb.MongoException;
import java.util.List;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AggregateService {

  private final MongoService mongoService;

  private final DocumentService documentService;

  @Autowired
  public AggregateService(MongoService mongoService, DocumentService documentService) {
    this.mongoService = mongoService;
    this.documentService = documentService;
  }

  @NonNull
  public List<ObjectNode> aggregate(@NonNull AggregateRequest request) {
    try {
      final var collection = mongoService.getCollection(request);
      final var pipeline = documentService.toBsons("pipeline", request.getPipeline());
      if (pipeline == null) {
        final var message = "Aggregate pipeline \"pipeline\" is missing.";
        throw new ResponseStatusException(BAD_REQUEST, message);
      }
      if (documentService.containsNulls(pipeline)) {
        final var message = "Aggregate pipeline \"pipeline\" contains null(s).";
        throw new ResponseStatusException(BAD_REQUEST, message);
      }
      final var aggregate = collection.aggregate(pipeline);
      return StreamSupport.stream(aggregate.spliterator(), false)
          .map(documentService::toObjectNode)
          .toList();
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }
}
