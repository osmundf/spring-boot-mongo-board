package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.data.CollationRecord;
import org.springframework.lang.Nullable;

/**
 * Find Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class FindRequest extends FilterRequest {

  private ObjectNode projection;

  private ObjectNode sort;

  private ObjectNode let;

  private ObjectNode hint;

  private CollationRecord collation;

  private Long maxTime;

  /**
   * Getter for projection.
   *
   * @return object node for projection
   */
  @Nullable
  public ObjectNode getProjection() {
    return projection;
  }

  /**
   * Setter for projection.
   *
   * @param projection object node for projection
   */
  public void setProjection(@Nullable ObjectNode projection) {
    this.projection = projection;
  }

  /**
   * Getter for sort.
   *
   * @return object node for sort
   */
  @Nullable
  public ObjectNode getSort() {
    return sort;
  }

  /**
   * Setter for sort.
   *
   * @param sort object node for sort
   */
  public void setSort(@Nullable ObjectNode sort) {
    this.sort = sort;
  }

  /**
   * Getter for let.
   *
   * @return object node for let
   */
  @Nullable
  public ObjectNode getLet() {
    return let;
  }

  /**
   * Setter for let.
   *
   * @param let object node for let
   */
  public void setLet(@Nullable ObjectNode let) {
    this.let = let;
  }

  /**
   * Getter for hint.
   *
   * @return object node for hint
   */
  @Nullable
  public ObjectNode getHint() {
    return hint;
  }

  /**
   * Setter for hint.
   *
   * @param hint object node for hint
   */
  public void setHint(@Nullable ObjectNode hint) {
    this.hint = hint;
  }

  /**
   * Getter for collation.
   *
   * @return collation record
   */
  @Nullable
  public CollationRecord getCollation() {
    return collation;
  }

  /**
   * Setter for collation.
   *
   * @param collation collation record
   */
  public void setCollation(@Nullable CollationRecord collation) {
    this.collation = collation;
  }

  /**
   * Getter for max time.
   *
   * @return max time duration in milliseconds
   */
  @Nullable
  public Long getMaxTime() {
    return maxTime;
  }

  /**
   * Setter for max time.
   *
   * @param maxTime max time duration in milliseconds
   */
  public void setMaxTime(@Nullable Long maxTime) {
    this.maxTime = maxTime;
  }
}
