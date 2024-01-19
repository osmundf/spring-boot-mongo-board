package com.github.osmundf.mongo.board.view.result;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serializable;

/**
 * Insert One Result Model.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class UpdateResultModel implements Serializable {

  public JsonNode insertId;

  public boolean acknowledged = false;

  public long matchedCount;

  public long modifiedCount;

  /**
   * Getter for insert Id.
   *
   * @return JSON node for insert Id
   */
  public JsonNode getInsertId() {
    return insertId;
  }

  /**
   * Setter for insert Id.
   *
   * @param insertId JSON node for insert Id
   */
  public void setInsertId(JsonNode insertId) {
    this.insertId = insertId;
  }

  /**
   * Getter for acknowledged.
   *
   * @return true if acknowledged, false otherwise
   */
  public boolean isAcknowledged() {
    return acknowledged;
  }

  /**
   * Setter for acknowledged.
   *
   * @param acknowledged acknowledged
   */
  public void setAcknowledged(boolean acknowledged) {
    this.acknowledged = acknowledged;
  }

  /**
   * Getter for matched count.
   *
   * @return update matched count
   */
  public long getMatchedCount() {
    return matchedCount;
  }

  /**
   * Setter for matched count.
   *
   * @param matchedCount update matched count
   */
  public void setMatchedCount(long matchedCount) {
    this.matchedCount = matchedCount;
  }

  /**
   * Getter for modified count.
   *
   * @return update modified count
   */
  public long getModifiedCount() {
    return modifiedCount;
  }

  /**
   * Setter for modified count.
   *
   * @param modifiedCount update modified count
   */
  public void setModifiedCount(long modifiedCount) {
    this.modifiedCount = modifiedCount;
  }
}
