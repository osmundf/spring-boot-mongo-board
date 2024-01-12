package com.github.osmundf.mongo.board.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

class DocumentServiceTest {

  private final ObjectMapper objectMapper = new ObjectMapper();

  private final DocumentService documentService = new DocumentService(objectMapper);

  @Test
  void parseBson() throws JsonProcessingException {
    final var objectNode = objectMapper.readValue("{}", ObjectNode.class);
    System.out.println(documentService.toDocument("blank", objectNode));
  }
}
