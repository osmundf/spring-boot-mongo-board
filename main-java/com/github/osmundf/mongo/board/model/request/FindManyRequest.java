package com.github.osmundf.mongo.board.model.request;

import org.springframework.lang.Nullable;

/**
 * Find Many Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class FindManyRequest extends FindOneRequest {

  private Integer limit;

  private Integer offset;

  /**
   * Getter for limit.
   *
   * @return limit value, null otherwise
   */
  @Nullable
  public Integer getLimit() {
    return limit;
  }

  /**
   * Setter for limit.
   *
   * @param limit limit value
   */
  public void setLimit(@Nullable Integer limit) {
    this.limit = limit;
  }

  /**
   * Getter for offset.
   *
   * @return offset value, null otherwise
   */
  @Nullable
  public Integer getOffset() {
    return offset;
  }

  /**
   * Setter for offset.
   *
   * @param offset offset value
   */
  public void setOffset(@Nullable Integer offset) {
    this.offset = offset;
  }
}
