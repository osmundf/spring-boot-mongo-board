package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.springframework.lang.Nullable;

/**
 * Aggregate Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class AggregateRequest extends CollectionRequest {

  private List<ObjectNode> pipeline;

  /**
   * Getter for pipeline.
   *
   * @return list of object nodes for pipeline
   */
  @Nullable
  public List<ObjectNode> getPipeline() {
    return pipeline;
  }

  /**
   * Setter for pipeline.
   *
   * @param pipeline list of object nodes for pipeline
   */
  public void setPipeline(@Nullable List<ObjectNode> pipeline) {
    this.pipeline = pipeline;
  }
}
