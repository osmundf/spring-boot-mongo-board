package com.github.osmundf.mongo.board.model.bulk;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.Serializable;
import org.springframework.lang.Nullable;

/**
 * Insert Bulk Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class InsertBulkRequest implements Serializable {

  private ObjectNode document;

  /**
   * Getter for document.
   *
   * @return object node for document
   */
  @Nullable
  public ObjectNode getDocument() {
    return document;
  }

  /**
   * Setter for document.
   *
   * @param document object node for document
   */
  public void setDocument(@Nullable ObjectNode document) {
    this.document = document;
  }
}
