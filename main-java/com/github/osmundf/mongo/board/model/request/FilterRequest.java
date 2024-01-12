package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.lang.Nullable;

public class FilterRequest extends CollectionRequest {

  private ObjectNode filter;

  @Nullable
  public ObjectNode getFilter() {
    return filter;
  }

  public void setFilter(@Nullable ObjectNode filter) {
    this.filter = filter;
  }
}
