package com.github.osmundf.mongo.board.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osmundf.mongo.board.model.request.InsertManyRequest;
import com.github.osmundf.mongo.board.model.request.InsertOneRequest;
import com.github.osmundf.mongo.board.service.InsertService;
import com.github.osmundf.mongo.board.view.result.InsertManyResultModel;
import com.github.osmundf.mongo.board.view.result.InsertOneResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Insert Controller.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@RestController
@RequestMapping("/")
public class InsertController {

  private final ObjectMapper objectMapper;

  private final InsertService insertService;

  /**
   * Constructor for Insert Controller.
   *
   * @param objectMapper object mapper
   * @param insertService insert service
   */
  @Autowired
  public InsertController(ObjectMapper objectMapper, InsertService insertService) {
    this.objectMapper = objectMapper;
    this.insertService = insertService;
  }

  /**
   * Perform insert one operation.
   *
   * @param body body for insert one request
   * @see com.github.osmundf.mongo.board.model.request.InsertOneRequest
   * @return response entity with insert one result
   */
  @PostMapping("insertOne")
  public ResponseEntity<InsertOneResultModel> insertOne(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, InsertOneRequest.class);
      return ResponseEntity.ok(insertService.insertOne(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  /**
   * Perform insert many operation.
   *
   * @param body body for insert many request
   * @see com.github.osmundf.mongo.board.model.request.InsertManyRequest
   * @return response entity with insert many result
   */
  @PostMapping("insertMany")
  public ResponseEntity<InsertManyResultModel> insertMany(@RequestBody String body) {

    try {
      final var request = objectMapper.readValue(body, InsertManyRequest.class);
      return ResponseEntity.ok(insertService.insertMany(request));
    }
    // Catch JSON exceptions.
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }
}
