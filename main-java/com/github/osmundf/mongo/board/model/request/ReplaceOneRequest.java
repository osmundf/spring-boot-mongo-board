package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.data.CollationRecord;
import org.springframework.lang.Nullable;

/**
 * Replace One Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class ReplaceOneRequest extends FilterRequest {

  private ObjectNode document;

  private boolean upsert = false;

  private ObjectNode let;

  private ObjectNode hint;

  private CollationRecord collation;

  private boolean validate = true;

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

  /**
   * Setter for collation.
   *
   * @param collation collation record
   */
  public void setCollation(@Nullable CollationRecord collation) {
    this.collation = collation;
  }

  /**
   * Getter for validate.
   *
   * @return true if validated, false otherwise
   */
  public boolean isValidate() {
    return validate;
  }

  /**
   * Setter for validate.
   *
   * @param validate validate
   */
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
