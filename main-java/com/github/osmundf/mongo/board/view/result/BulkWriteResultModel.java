package com.github.osmundf.mongo.board.view.result;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;

public class BulkWriteResultModel {

  private Map<Integer, JsonNode> inserts;
  private Map<Integer, JsonNode> upserts;
  private int matchedCount;
  private int insertedCount;
  private int modifiedCount;
  private int deletedCount;

  public Map<Integer, JsonNode> getInserts() {
    return inserts;
  }

  public void setInserts(Map<Integer, JsonNode> inserts) {
    this.inserts = inserts;
  }

  public Map<Integer, JsonNode> getUpserts() {
    return upserts;
  }

  public void setUpserts(Map<Integer, JsonNode> upserts) {
    this.upserts = upserts;
  }

  public int getMatchedCount() {
    return matchedCount;
  }

  public void setMatchedCount(int matchedCount) {
    this.matchedCount = matchedCount;
  }

  public int getInsertedCount() {
    return insertedCount;
  }

  public void setInsertedCount(int insertedCount) {
    this.insertedCount = insertedCount;
  }

  public int getModifiedCount() {
    return modifiedCount;
  }

  public void setModifiedCount(int modifiedCount) {
    this.modifiedCount = modifiedCount;
  }

  public int getDeletedCount() {
    return deletedCount;
  }

  public void setDeletedCount(int deletedCount) {
    this.deletedCount = deletedCount;
  }
}
