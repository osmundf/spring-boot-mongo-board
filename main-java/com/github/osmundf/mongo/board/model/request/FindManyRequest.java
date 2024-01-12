package com.github.osmundf.mongo.board.model.request;

import org.springframework.lang.Nullable;

public class FindManyRequest extends FindOneRequest {

  private Integer limit;

  private Integer offset;

  @Nullable
  public Integer getLimit() {
    return limit;
  }

  public void setLimit(@Nullable Integer limit) {
    this.limit = limit;
  }

  @Nullable
  public Integer getOffset() {
    return offset;
  }

  public void setOffset(@Nullable Integer offset) {
    this.offset = offset;
  }
}
