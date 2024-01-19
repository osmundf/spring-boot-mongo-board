package com.github.osmundf.mongo.board.view.result;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;

/**
 * Insert One Result Model.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class InsertOneResultModel {

  private JsonNode id;

  private boolean acknowledged;

  /**
   * Getter for Id.
   *
   * @return JSON node for Id
   */
  @Nullable
  public JsonNode getId() {
    return id;
  }

  /**
   * Setter for Id.
   *
   * @param id JSON node for Id
   */
  public void setId(@Nullable JsonNode id) {
    this.id = id;
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
