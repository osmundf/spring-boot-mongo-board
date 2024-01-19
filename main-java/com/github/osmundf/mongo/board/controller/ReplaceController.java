package com.github.osmundf.mongo.board.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.FindOneAndReplaceRequest;
import com.github.osmundf.mongo.board.model.request.ReplaceOneRequest;
import com.github.osmundf.mongo.board.service.ReplaceService;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Replace Controller.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@RestController
@RequestMapping("/")
public class ReplaceController {

  private final ObjectMapper objectMapper;

  private final ReplaceService replaceService;

  /**
   * Constructor for Replace Controller.
   *
   * @param objectMapper object mapper
   * @param replaceService replace service
   */
  @Autowired
  public ReplaceController(ObjectMapper objectMapper, ReplaceService replaceService) {
    this.objectMapper = objectMapper;
    this.replaceService = replaceService;
  }

  /**
   * Perform find one and replace operation.
   *
   * @param body body for find one and replace request
   * @see com.github.osmundf.mongo.board.model.request.FindOneAndReplaceRequest
   * @return response entity with result, no content otherwise
   */
  @PostMapping("findOneAndReplace")
  public ResponseEntity<ObjectNode> findOneAndReplace(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, FindOneAndReplaceRequest.class);
      final var result = replaceService.findOneAndReplace(request);
      return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  /**
   * Perform replace one operation.
   *
   * @param body body for replace one request
   * @see com.github.osmundf.mongo.board.model.request.ReplaceOneRequest
   * @return response entity with update result
   */
  @PostMapping("replaceOne")
  public ResponseEntity<UpdateResult> replaceOne(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, ReplaceOneRequest.class);
      return ResponseEntity.ok(replaceService.replaceOne(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }
}
