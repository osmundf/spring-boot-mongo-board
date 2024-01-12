package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.data.CollationRecord;
import org.springframework.lang.Nullable;

public class FindRequest extends FilterRequest {

  private ObjectNode projection;

  private ObjectNode sort;

  private ObjectNode let;

  private ObjectNode hint;

  private CollationRecord collation;

  private Long maxTime;

  @Nullable
  public ObjectNode getProjection() {
    return projection;
  }

  public void setProjection(@Nullable ObjectNode projection) {
    this.projection = projection;
  }

  @Nullable
  public ObjectNode getSort() {
    return sort;
  }

  public void setSort(@Nullable ObjectNode sort) {
    this.sort = sort;
  }

  @Nullable
  public ObjectNode getLet() {
    return let;
  }

  public void setLet(@Nullable ObjectNode let) {
    this.let = let;
  }

  @Nullable
  public ObjectNode getHint() {
    return hint;
  }

  public void setHint(@Nullable ObjectNode hint) {
    this.hint = hint;
  }

  @Nullable
  public CollationRecord getCollation() {
    return collation;
  }

  public void setCollation(@Nullable CollationRecord collation) {
    this.collation = collation;
  }

  @Nullable
  public Long getMaxTime() {
    return maxTime;
  }

  public void setMaxTime(@Nullable Long maxTime) {
    this.maxTime = maxTime;
  }
}
