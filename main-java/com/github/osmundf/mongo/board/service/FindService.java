package com.github.osmundf.mongo.board.service;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.FindManyRequest;
import com.github.osmundf.mongo.board.model.request.FindOneRequest;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import java.util.List;
import java.util.stream.StreamSupport;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FindService {

  private final MongoService mongoService;

  private final DocumentService documentService;

  private final CollationService collationService;

  @Autowired
  public FindService(
      MongoService mongoService,
      DocumentService documentService,
      CollationService collationService) {
    this.mongoService = mongoService;
    this.documentService = documentService;
    this.collationService = collationService;
  }

  @NonNull
  public List<ObjectNode> find(@NonNull FindManyRequest request) {
    try {
      final var find = applyFindParameters(request);
      if (request.getOffset() != null) {
        find.skip(request.getOffset());
      }
      if (request.getLimit() != null) {
        find.limit(request.getLimit());
      }
      return StreamSupport.stream(find.spliterator(), false)
          .map(documentService::toObjectNode)
          .toList();
    }
    // Catch JSON and Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  @Nullable
  public ObjectNode findOne(@NonNull FindOneRequest request) {
    try {
      final var find = applyFindParameters(request);
      final var document = find.first();
      return document != null ? documentService.toObjectNode(document) : null;
    }
    // Catch Mongo exceptions.
    catch (MongoException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  @NonNull
  private FindIterable<Document> applyFindParameters(@NonNull FindOneRequest request) {

    final var collection = mongoService.getCollection(request);

    final var find = collection.find();

    if (request.getFilter() != null) {
      find.filter(documentService.toBson("filter", request.getFilter()));
    }
    if (request.getProjection() != null) {
      find.projection(documentService.toBson("projection", request.getProjection()));
    }
    if (request.getSort() != null) {
      find.sort(documentService.toBson("sort", request.getSort()));
    }
    if (request.getLet() != null) {
      find.let(documentService.toBson("let", request.getLet()));
    }
    if (request.getHint() != null) {
      find.hint(documentService.toBson("hint", request.getHint()));
    }
    if (request.getCollation() != null) {
      find.collation(collationService.toCollation(request.getCollation()));
    }

    if (request.getMin() != null) {
      find.min(documentService.toBson("min", request.getMin()));
    }

    if (request.getMax() != null) {
      find.max(documentService.toBson("max", request.getMax()));
    }

    find.allowDiskUse(request.isAllowDiskUsage());

    find.partial(request.isPartial());

    if (request.getMaxTime() != null) {
      find.maxTime(request.getMaxTime(), MILLISECONDS);
    }

    return find;
  }
}
