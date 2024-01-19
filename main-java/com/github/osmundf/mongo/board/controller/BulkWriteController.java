package com.github.osmundf.mongo.board.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osmundf.mongo.board.model.bulk.BulkWriteRequest;
import com.github.osmundf.mongo.board.service.BulkWriteService;
import com.github.osmundf.mongo.board.view.result.BulkWriteResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Bulk Write Controller.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@RestController
@RequestMapping("/")
public class BulkWriteController {

  private final ObjectMapper objectMapper;

  private final BulkWriteService bulkWriteService;

  /**
   * Constructor for Bulk Write Controller.
   *
   * @param objectMapper object mapper
   * @param bulkWriteService bulk write service
   */
  @Autowired
  public BulkWriteController(ObjectMapper objectMapper, BulkWriteService bulkWriteService) {
    this.objectMapper = objectMapper;
    this.bulkWriteService = bulkWriteService;
  }

  /**
   * Perform bulk write operation.
   *
   * @param body body for bulk write request
   * @see com.github.osmundf.mongo.board.model.bulk.BulkWriteRequest
   * @return response entity with bulk write result
   */
  @PostMapping("bulkWrite")
  public ResponseEntity<BulkWriteResultModel> bulkWrite(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, BulkWriteRequest.class);
      return ResponseEntity.ok(bulkWriteService.bulkWrite(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }
}
