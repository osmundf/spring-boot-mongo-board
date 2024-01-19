package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.lang.Nullable;

/**
 * Insert One Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class InsertOneRequest extends CollectionRequest {

  private ObjectNode document;

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
