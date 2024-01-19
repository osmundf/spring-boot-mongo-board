package com.github.osmundf.mongo.board.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.AggregateRequest;
import com.github.osmundf.mongo.board.service.AggregateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Aggregate Controller.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@RestController
@RequestMapping("/")
public class AggregateController {

  private final ObjectMapper objectMapper;

  private final AggregateService aggregateService;

  /**
   * Public constructor.
   *
   * @param objectMapper object mapper
   * @param aggregateService aggregate service
   */
  @Autowired
  public AggregateController(ObjectMapper objectMapper, AggregateService aggregateService) {
    this.objectMapper = objectMapper;
    this.aggregateService = aggregateService;
  }

  /**
   * Perform aggregate operation.
   *
   * @param body body for aggregate request model
   * @see com.github.osmundf.mongo.board.model.request.AggregateRequest
   * @return response entity with the list of aggregation pipeline results
   */
  @PostMapping("aggregate")
  public ResponseEntity<List<ObjectNode>> aggregate(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, AggregateRequest.class);
      return ResponseEntity.ok(aggregateService.aggregate(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }
}
