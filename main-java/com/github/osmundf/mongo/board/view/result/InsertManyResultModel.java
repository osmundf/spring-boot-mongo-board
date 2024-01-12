package com.github.osmundf.mongo.board.view.result;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;

public class InsertManyResultModel {

  private Map<Integer, JsonNode> ids;

  private boolean acknowledged;

  public Map<Integer, JsonNode> getIds() {
    return ids;
  }

  public void setIds(Map<Integer, JsonNode> ids) {
    this.ids = ids;
  }

  public boolean isAcknowledged() {
    return acknowledged;
  }

  public void setAcknowledged(boolean acknowledged) {
    this.acknowledged = acknowledged;
  }
}
