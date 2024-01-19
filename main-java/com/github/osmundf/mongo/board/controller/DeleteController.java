package com.github.osmundf.mongo.board.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.DeleteRequest;
import com.github.osmundf.mongo.board.model.request.FindOneAndDeleteRequest;
import com.github.osmundf.mongo.board.service.DeleteService;
import com.github.osmundf.mongo.board.view.result.DeleteResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Delete Controller.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@RestController
@RequestMapping("/")
public class DeleteController {

  private final ObjectMapper objectMapper;

  private final DeleteService deleteService;

  /**
   * Constructor for Delete Controller.
   *
   * @param objectMapper object mapper
   * @param deleteService delete service
   */
  @Autowired
  public DeleteController(ObjectMapper objectMapper, DeleteService deleteService) {
    this.objectMapper = objectMapper;
    this.deleteService = deleteService;
  }

  /**
   * Perform find one and delete operation.
   *
   * @param body body for find one and delete request
   * @see com.github.osmundf.mongo.board.model.request.FindOneAndDeleteRequest
   * @return response entity with result, no content response entity otherwise
   */
  @PostMapping("findOneAndDelete")
  public ResponseEntity<ObjectNode> findOneAndDelete(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, FindOneAndDeleteRequest.class);
      final var result = deleteService.findOneAndDelete(request);
      return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  /**
   * Perform delete one operation.
   *
   * @param body body for delete request
   * @see com.github.osmundf.mongo.board.model.request.DeleteRequest
   * @return response entity with delete result
   */
  @PostMapping("deleteOne")
  public ResponseEntity<DeleteResultModel> deleteOne(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, DeleteRequest.class);
      return ResponseEntity.ok(deleteService.deleteOne(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  /**
   * Perform delete many operation.
   *
   * @param body body with delete request
   * @see com.github.osmundf.mongo.board.model.request.DeleteRequest
   * @return response entity with delete result
   */
  @PostMapping("deleteMany")
  public ResponseEntity<DeleteResultModel> deleteMany(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, DeleteRequest.class);
      return ResponseEntity.ok(deleteService.deleteMany(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }
}
