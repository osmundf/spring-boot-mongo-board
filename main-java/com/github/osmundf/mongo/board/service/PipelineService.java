package com.github.osmundf.mongo.board.service;

import static com.github.osmundf.mongo.board.model.data.UpdateProcessType.UPDATE_WITH_DOCUMENT;
import static com.github.osmundf.mongo.board.model.data.UpdateProcessType.UPDATE_WITH_PIPELINE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.data.UpdateOrPipeline;
import java.util.Collections;
import java.util.List;
import org.bson.BsonDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PipelineService {

  private final DocumentService documentService;

  @Autowired
  public PipelineService(DocumentService documentService) {
    this.documentService = documentService;
  }

  @NonNull
  public UpdateOrPipeline getUpdateOrPipeline(
      @NonNull String operation, @Nullable ObjectNode document, @Nullable List<ObjectNode> list) {

    if (document == null && list == null) {
      final var message = String.format("%s \"update\" and \"pipeline\" are missing.", operation);
      throw new ResponseStatusException(BAD_REQUEST, message);
    }

    if (document != null && list == null) {
      {
        final var update = documentService.toBson("update", document);
        if (update == null) {
          final var message = String.format("%s \"update\" is missing.", operation);
          throw new ResponseStatusException(BAD_REQUEST, message);
        }
        return new UpdateOrPipeline(UPDATE_WITH_DOCUMENT, update, Collections.emptyList());
      }
    }

    final var pipeline = documentService.toBsons("pipeline", list);
    if (pipeline == null) {
      final var message = String.format("%s \"pipeline\" is missing.", operation);
      throw new ResponseStatusException(BAD_REQUEST, message);
    }
    if (documentService.containsNulls(pipeline)) {
      final var message = String.format("%s \"pipeline\" contains null(s).", operation);
      throw new ResponseStatusException(BAD_REQUEST, message);
    }
    return new UpdateOrPipeline(UPDATE_WITH_PIPELINE, new BsonDocument(), pipeline);
  }
}
