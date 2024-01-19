package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.lang.Nullable;

/**
 * Find One Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class FindOneRequest extends FindRequest {

  private ObjectNode min;

  private ObjectNode max;

  private boolean allowDiskUsage = false;

  private boolean partial = false;

  /**
   * Getter for min.
   *
   * @return object node for min
   */
  @Nullable
  public ObjectNode getMin() {
    return min;
  }

  /**
   * Setter for min.
   *
   * @param min object node for min
   */
  public void setMin(@Nullable ObjectNode min) {
    this.min = min;
  }

  /**
   * Getter for max.
   *
   * @return object node for max
   */
  @Nullable
  public ObjectNode getMax() {
    return max;
  }

  /**
   * Setter for max.
   *
   * @param max object node for max
   */
  public void setMax(@Nullable ObjectNode max) {
    this.max = max;
  }

  /**
   * Getter for allow disk usage.
   *
   * @return true for allowing disk usage, false otherwise
   */
  public boolean isAllowDiskUsage() {
    return allowDiskUsage;
  }

  /**
   * Setter for allow disk usage.
   *
   * @param allowDiskUsage allow disk usage
   */
  public void setAllowDiskUsage(boolean allowDiskUsage) {
    this.allowDiskUsage = allowDiskUsage;
  }

  /**
   * Getter for partial.
   *
   * @return true for partial, false otherwise
   */
  public boolean isPartial() {
    return partial;
  }

  /**
   * Setter for partial.
   *
   * @param partial partial
   */
  public void setPartial(boolean partial) {
    this.partial = partial;
  }
}
