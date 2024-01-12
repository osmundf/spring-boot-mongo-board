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

@RestController
@RequestMapping("/")
public class ReplaceController {

  private final ObjectMapper objectMapper;

  private final ReplaceService replaceService;

  @Autowired
  public ReplaceController(ObjectMapper objectMapper, ReplaceService replaceService) {
    this.objectMapper = objectMapper;
    this.replaceService = replaceService;
  }

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
