package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.data.CollationRecord;
import java.util.List;
import org.springframework.lang.Nullable;

/**
 * Update Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class UpdateRequest extends FilterRequest {

  private ObjectNode update;

  private List<ObjectNode> pipeline;

  private List<ObjectNode> arrayFilters;

  private boolean upsert = false;

  private ObjectNode let;

  private ObjectNode hint;

  private boolean validate = true;

  private CollationRecord collation;

  /**
   * Getter for update.
   *
   * @return object node for update document
   */
  @Nullable
  public ObjectNode getUpdate() {
    return update;
  }

  /**
   * Setter for update.
   *
   * @param update object node for update document
   */
  public void setUpdate(@Nullable ObjectNode update) {
    this.update = update;
  }

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

  /**
   * Getter for array filters.
   *
   * @return list of object nodes for array filters
   */
  @Nullable
  public List<ObjectNode> getArrayFilters() {
    return arrayFilters;
  }

  /**
   * Setter for array filters.
   *
   * @param arrayFilters list of object nodes for array filters
   */
  public void setArrayFilters(@Nullable List<ObjectNode> arrayFilters) {
    this.arrayFilters = arrayFilters;
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
