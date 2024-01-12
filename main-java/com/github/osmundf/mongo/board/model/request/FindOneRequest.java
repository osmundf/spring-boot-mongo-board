package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.lang.Nullable;

public class FindOneRequest extends FindRequest {

  private ObjectNode min;

  private ObjectNode max;

  private boolean allowDiskUsage = false;

  private boolean partial = false;

  @Nullable
  public ObjectNode getMin() {
    return min;
  }

  public void setMin(@Nullable ObjectNode min) {
    this.min = min;
  }

  @Nullable
  public ObjectNode getMax() {
    return max;
  }

  public void setMax(@Nullable ObjectNode max) {
    this.max = max;
  }

  public boolean isAllowDiskUsage() {
    return allowDiskUsage;
  }

  public void setAllowDiskUsage(boolean allowDiskUsage) {
    this.allowDiskUsage = allowDiskUsage;
  }

  public boolean isPartial() {
    return partial;
  }

  public void setPartial(boolean partial) {
    this.partial = partial;
  }
}
