package com.github.osmundf.mongo.board.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.FindManyRequest;
import com.github.osmundf.mongo.board.model.request.FindOneRequest;
import com.github.osmundf.mongo.board.service.FindService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Find Controller.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@RestController
@RequestMapping("/")
public class FindController {

  private final ObjectMapper objectMapper;

  private final FindService findService;

  /**
   * Constructor for Find Controller.
   *
   * @param objectMapper object mapper
   * @param findService find service
   */
  @Autowired
  public FindController(ObjectMapper objectMapper, FindService findService) {
    this.objectMapper = objectMapper;
    this.findService = findService;
  }

  /**
   * Return list of matching documents.
   *
   * @param body body for find many request
   * @see com.github.osmundf.mongo.board.model.request.FindManyRequest
   * @return list of matching documents, empty list otherwise.
   */
  @PostMapping("find")
  public ResponseEntity<List<ObjectNode>> find(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, FindManyRequest.class);
      return ResponseEntity.ok(findService.find(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  /**
   * Return first matching document.
   *
   * @param body body for find one request
   * @see com.github.osmundf.mongo.board.model.request.FindOneRequest
   * @return response entity with first matching document, no content otherwise.
   */
  @PostMapping("findOne")
  public ResponseEntity<ObjectNode> findOne(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, FindOneRequest.class);
      final var result = findService.findOne(request);
      return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }
}
