package com.github.osmundf.mongo.board.model.bulk;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.data.CollationRecord;
import java.io.Serializable;
import org.springframework.lang.Nullable;

/**
 * Delete Bulk Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class DeleteBulkRequest implements Serializable {

  private ObjectNode filter;

  private ObjectNode let;

  private ObjectNode hint;

  private CollationRecord collation;

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

  /**
   * Getter for let.
   *
   * @return object node for let
   */
  @Nullable
  public ObjectNode getLet() {
    return let;
  }

  /**
   * Setter for let.
   *
   * @param let object node for let
   */
  public void setLet(@Nullable ObjectNode let) {
    this.let = let;
  }

  /**
   * Getter for hint.
   *
   * @return object node for hint
   */
  @Nullable
  public ObjectNode getHint() {
    return hint;
  }

  /**
   * Setter for hint.
   *
   * @param hint object node for hint
   */
  public void setHint(@Nullable ObjectNode hint) {
    this.hint = hint;
  }

  /**
   * Getter for collation.
   *
   * @return collation record
   */
  @Nullable
  public CollationRecord getCollation() {
    return collation;
  }

  /**
   * Setter for collation.
   *
   * @param collation collation record
   */
  public void setCollation(@Nullable CollationRecord collation) {
    this.collation = collation;
  }
}
