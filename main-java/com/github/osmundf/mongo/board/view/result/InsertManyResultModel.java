package com.github.osmundf.mongo.board.view.result;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;

/**
 * Insert Many Result Model.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class InsertManyResultModel {

  private Map<Integer, JsonNode> ids;

  private boolean acknowledged;

  /**
   * Getter for Ids.
   *
   * @return a map of index to JSON node for ids
   */
  public Map<Integer, JsonNode> getIds() {
    return ids;
  }

  /**
   * Setter for Ids.
   *
   * @param ids a map of index to JSON node for ids
   */
  public void setIds(Map<Integer, JsonNode> ids) {
    this.ids = ids;
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
}
