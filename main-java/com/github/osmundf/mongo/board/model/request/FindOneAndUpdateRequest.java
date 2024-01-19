package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.springframework.lang.Nullable;

/**
 * Find One And Update Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class FindOneAndUpdateRequest extends FindRequest {

  private ObjectNode update;

  private List<ObjectNode> pipeline;

  private List<ObjectNode> arrayFilters;

  private String returnUpdate = "after";

  private boolean upsert = false;

  private boolean validate = true;

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
   * Getter for return update value.
   *
   * @return return update value
   */
  @Nullable
  public String getReturnUpdate() {
    return returnUpdate;
  }

  /**
   * Setter for return update value.
   *
   * @param returnUpdate return update value
   */
  public void setReturnUpdate(@Nullable String returnUpdate) {
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
