package com.github.osmundf.mongo.board.view.result;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;

public class InsertOneResultModel {

  private JsonNode id;

  private boolean acknowledged;

  @Nullable
  public JsonNode getId() {
    return id;
  }

  public void setId(@Nullable JsonNode id) {
    this.id = id;
  }

  public boolean isAcknowledged() {
    return acknowledged;
  }

  public void setAcknowledged(boolean acknowledged) {
    this.acknowledged = acknowledged;
  }
}
