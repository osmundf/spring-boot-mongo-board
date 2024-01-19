package com.github.osmundf.mongo.board.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.bulk.DeleteBulkRequest;
import com.github.osmundf.mongo.board.model.bulk.InsertBulkRequest;
import com.github.osmundf.mongo.board.model.bulk.ReplaceBulkRequest;
import com.github.osmundf.mongo.board.model.bulk.UpdateBulkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Bulk Document Service.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@Service
public class BulkDocumentService {

  private final ObjectMapper objectMapper;

  /**
   * Constructor for Bulk Document Service.
   *
   * @param objectMapper object mapper
   */
  @Autowired
  public BulkDocumentService(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * Return an insertion bulk request for a given object node.
   *
   * @param objectNode object node
   * @return insertion bulk request
   */
  @NonNull
  public InsertBulkRequest getInsert(@NonNull ObjectNode objectNode) {
    try {
      return objectMapper.readValue(objectNode.toString(), InsertBulkRequest.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Return an update bulk request for a given object node.
   *
   * @param objectNode object node
   * @return an update bulk request
   */
  @NonNull
  public UpdateBulkRequest getUpdate(@NonNull ObjectNode objectNode) {
    try {
      return objectMapper.readValue(objectNode.toString(), UpdateBulkRequest.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Return a replacement bulk request for a given object node.
   *
   * @param objectNode object node
   * @return a replacement bulk request
   */
  @NonNull
  public ReplaceBulkRequest getReplace(@NonNull ObjectNode objectNode) {
    try {
      return objectMapper.readValue(objectNode.toString(), ReplaceBulkRequest.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Return a deletion bulk request for a given object node.
   *
   * @param objectNode object node
   * @return a deletion bulk request
   */
  public DeleteBulkRequest getDelete(ObjectNode objectNode) {
    try {
      return objectMapper.readValue(objectNode.toString(), DeleteBulkRequest.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
