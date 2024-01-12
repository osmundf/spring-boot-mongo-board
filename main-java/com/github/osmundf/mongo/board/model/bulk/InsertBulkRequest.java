package com.github.osmundf.mongo.board.model.bulk;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.lang.Nullable;

public class InsertBulkRequest {

  private ObjectNode document;

  @Nullable
  public ObjectNode getDocument() {
    return document;
  }

  public void setDocument(@Nullable ObjectNode document) {
    this.document = document;
  }
}
