package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.lang.Nullable;

/**
 * Filter Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class FilterRequest extends CollectionRequest {

  private ObjectNode filter;

  /**
   * Getter for filter.
   *
   * @return object node for filter
   */
  @Nullable
  public ObjectNode getFilter() {
    return filter;
  }

  /**
   * Setter for filter.
   *
   * @param filter object node for filter
   */
  public void setFilter(@Nullable ObjectNode filter) {
    this.filter = filter;
  }
}
