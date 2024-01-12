package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.springframework.lang.Nullable;

public class AggregateRequest extends CollectionRequest {

  private List<ObjectNode> pipeline;

  @Nullable
  public List<ObjectNode> getPipeline() {
    return pipeline;
  }

  public void setPipeline(@Nullable List<ObjectNode> pipeline) {
    this.pipeline = pipeline;
  }
}
