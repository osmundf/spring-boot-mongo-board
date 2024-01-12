package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.springframework.lang.Nullable;

public class FindOneAndUpdateRequest extends FindRequest {

  private ObjectNode update;

  private List<ObjectNode> pipeline;

  private List<ObjectNode> arrayFilters;

  private String returnUpdate = "after";

  private boolean upsert = false;

  private boolean validate = true;

  @Nullable
  public ObjectNode getUpdate() {
    return update;
  }

  public void setUpdate(@Nullable ObjectNode update) {
    this.update = update;
  }

  @Nullable
  public List<ObjectNode> getPipeline() {
    return pipeline;
  }

  public void setPipeline(@Nullable List<ObjectNode> pipeline) {
    this.pipeline = pipeline;
  }

  @Nullable
  public List<ObjectNode> getArrayFilters() {
    return arrayFilters;
  }

  public void setArrayFilters(@Nullable List<ObjectNode> arrayFilters) {
    this.arrayFilters = arrayFilters;
  }

  @Nullable
  public String getReturnUpdate() {
    return returnUpdate;
  }

  public void setReturnUpdate(@Nullable String returnUpdate) {
    this.returnUpdate = returnUpdate;
  }

  public boolean isUpsert() {
    return upsert;
  }

  public void setUpsert(boolean upsert) {
    this.upsert = upsert;
  }

  public boolean isValidate() {
    return validate;
  }

  public void setValidate(boolean validate) {
    this.validate = validate;
  }

  public boolean bypassDocumentValidation() {
    return !validate;
  }
}
