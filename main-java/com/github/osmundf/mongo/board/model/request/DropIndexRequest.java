package com.github.osmundf.mongo.board.model.request;

import org.springframework.lang.Nullable;

public class DropIndexRequest extends CollectionRequest {

  private String name;

  private Long maxTime;

  @Nullable
  public String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    this.name = name;
  }

  public Long getMaxTime() {
    return maxTime;
  }

  public void setMaxTime(Long maxTime) {
    this.maxTime = maxTime;
  }
}
