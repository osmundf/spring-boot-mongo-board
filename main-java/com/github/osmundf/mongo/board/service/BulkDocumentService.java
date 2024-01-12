package com.github.osmundf.mongo.board.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.bulk.DeleteBulkRequest;
import com.github.osmundf.mongo.board.model.bulk.InsertBulkRequest;
import com.github.osmundf.mongo.board.model.bulk.ReplaceBulkRequest;
import com.github.osmundf.mongo.board.model.bulk.UpdateBulkRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class BulkDocumentService {

  private final ObjectMapper objectMapper;

  public BulkDocumentService(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @NonNull
  public InsertBulkRequest getInsert(@NonNull ObjectNode objectNode) {
    try {
      return objectMapper.readValue(objectNode.toString(), InsertBulkRequest.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @NonNull
  public UpdateBulkRequest getUpdate(@NonNull ObjectNode objectNode) {
    try {
      return objectMapper.readValue(objectNode.toString(), UpdateBulkRequest.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @NonNull
  public ReplaceBulkRequest getReplace(@NonNull ObjectNode objectNode) {
    try {
      return objectMapper.readValue(objectNode.toString(), ReplaceBulkRequest.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public DeleteBulkRequest getDelete(ObjectNode objectNode) {
    try {
      return objectMapper.readValue(objectNode.toString(), DeleteBulkRequest.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
