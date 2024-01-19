package com.github.osmundf.mongo.board.model.bulk;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.data.CollationRecord;
import java.io.Serializable;
import org.springframework.lang.Nullable;

/**
 * Replace Bulk Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class ReplaceBulkRequest implements Serializable {

  private ObjectNode filter;

  private ObjectNode document;

  private boolean upsert = false;

  private ObjectNode let;

  private ObjectNode hint;

  private CollationRecord collation;

  private boolean validate = true;

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
   * Setter for filter
   *
   * @param filter object node for filter
   */
  public void setFilter(@Nullable ObjectNode filter) {
    this.filter = filter;
  }

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

  /**
   * Getter for upsert.
   *
   * @return true for upsert, false otherwise
   */
  public boolean isUpsert() {
    return upsert;
  }

  /**
   * Setter for upsert.
   *
   * @param upsert upsert
   */
  public void setUpsert(boolean upsert) {
    this.upsert = upsert;
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

  /** {@inheritDoc} */
  public void setCollation(@Nullable CollationRecord collation) {
    this.collation = collation;
  }

  /**
   * Getter for validate.
   *
   * @return true if validation is set, false otherwise
   */
  public boolean isValidate() {
    return validate;
  }

  /** {@inheritDoc} */
  public void setValidate(boolean validate) {
    this.validate = validate;
  }

  /**
   * Getter for bypassing document validation.
   *
   * @return true if document validation is bypassed, false otherwise
   */
  public boolean bypassDocumentValidation() {
    return !validate;
  }
}
