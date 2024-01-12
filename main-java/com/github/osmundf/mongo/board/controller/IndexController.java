package com.github.osmundf.mongo.board.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.CollectionRequest;
import com.github.osmundf.mongo.board.model.request.CreateIndexRequest;
import com.github.osmundf.mongo.board.model.request.DropIndexRequest;
import com.github.osmundf.mongo.board.service.IndexService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/")
public class IndexController {

  private final ObjectMapper objectMapper;

  private final IndexService indexService;

  @Autowired
  public IndexController(ObjectMapper objectMapper, IndexService indexService) {
    this.objectMapper = objectMapper;
    this.indexService = indexService;
  }

  @PostMapping("listIndexes")
  public ResponseEntity<List<ObjectNode>> listIndexes(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, CollectionRequest.class);
      return ResponseEntity.ok(indexService.listIndexes(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  @PostMapping("createIndex")
  public ResponseEntity<String> createIndex(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, CreateIndexRequest.class);
      return ResponseEntity.ok(indexService.createIndex(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  @PostMapping("dropIndex")
  public ResponseEntity<Void> dropIndex(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, DropIndexRequest.class);
      return ResponseEntity.ok(indexService.dropIndex(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }
}
