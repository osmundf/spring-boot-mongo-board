package com.github.osmundf.mongo.board.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.FindOneAndUpdateRequest;
import com.github.osmundf.mongo.board.model.request.UpdateRequest;
import com.github.osmundf.mongo.board.service.UpdateService;
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
public class UpdateController {

  private final ObjectMapper objectMapper;

  private final UpdateService updateService;

  @Autowired
  public UpdateController(ObjectMapper objectMapper, UpdateService updateService) {
    this.objectMapper = objectMapper;
    this.updateService = updateService;
  }

  @PostMapping("findOneAndUpdate")
  public ResponseEntity<ObjectNode> findOneAndUpdate(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, FindOneAndUpdateRequest.class);
      final var result = updateService.findOneAndUpdate(request);
      return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  @PostMapping("updateOne")
  public ResponseEntity<UpdateResult> updateOne(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, UpdateRequest.class);
      return ResponseEntity.ok(updateService.updateOne(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  @PostMapping("updateMany")
  public ResponseEntity<UpdateResult> updateMany(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, UpdateRequest.class);
      return ResponseEntity.ok(updateService.updateMany(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }
}
