package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.lang.Nullable;

/**
 * Find One And Replace Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class FindOneAndReplaceRequest extends FindRequest {

  private ObjectNode document;

  private String returnUpdate = "after";

  private boolean upsert = false;

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
   * Getter for return update value.
   *
   * @return return update value
   */
  public String getReturnUpdate() {
    return returnUpdate;
  }

  /**
   * Setter for return update value.
   *
   * @param returnUpdate return update value
   */
  public void setReturnUpdate(String returnUpdate) {
    this.returnUpdate = returnUpdate;
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
