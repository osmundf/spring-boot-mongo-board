package com.github.osmundf.mongo.board.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.FindOneAndUpdateRequest;
import com.github.osmundf.mongo.board.model.request.UpdateRequest;
import com.github.osmundf.mongo.board.service.UpdateService;
import com.github.osmundf.mongo.board.view.result.UpdateResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Update Controller.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@RestController
@RequestMapping("/")
public class UpdateController {

  private final ObjectMapper objectMapper;

  private final UpdateService updateService;

  /**
   * Constructor for Update Controller.
   *
   * @param objectMapper object mapper
   * @param updateService update service
   */
  @Autowired
  public UpdateController(ObjectMapper objectMapper, UpdateService updateService) {
    this.objectMapper = objectMapper;
    this.updateService = updateService;
  }

  /**
   * Perform find one and update operation.
   *
   * @param body body for find one and update request
   * @see com.github.osmundf.mongo.board.model.request.FindOneAndUpdateRequest
   * @return response entity with result, no content otherwise
   */
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

  /**
   * Perform update one operation.
   *
   * @param body body for update request
   * @see com.github.osmundf.mongo.board.model.request.UpdateRequest
   * @return response entity with update result
   */
  @PostMapping("updateOne")
  public ResponseEntity<UpdateResultModel> updateOne(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, UpdateRequest.class);
      return ResponseEntity.ok(updateService.updateOne(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  /**
   * Perform update many operation.
   *
   * @param body body for update request
   * @see com.github.osmundf.mongo.board.model.request.UpdateRequest
   * @return response entity with update result
   */
  @PostMapping("updateMany")
  public ResponseEntity<UpdateResultModel> updateMany(@RequestBody String body) {

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
