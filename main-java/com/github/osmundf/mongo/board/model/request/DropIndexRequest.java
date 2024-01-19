package com.github.osmundf.mongo.board.model.request;

import org.springframework.lang.Nullable;

/**
 * Drop Index Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class DropIndexRequest extends CollectionRequest {

  private String name;

  private Long maxTime;

  /**
   * Getter for name.
   *
   * @return name
   */
  @Nullable
  public String getName() {
    return name;
  }

  /**
   * Setter for name.
   *
   * @param name name
   */
  public void setName(@Nullable String name) {
    this.name = name;
  }

  /**
   * Getter for max time.
   *
   * @return max time duration in milliseconds
   */
  public Long getMaxTime() {
    return maxTime;
  }

  /**
   * Setter for max time.
   *
   * @param maxTime max time duration in milliseconds
   */
  public void setMaxTime(Long maxTime) {
    this.maxTime = maxTime;
  }
}
