package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.data.CollationRecord;
import java.util.List;
import org.springframework.lang.Nullable;

public class UpdateRequest extends FilterRequest {

  private ObjectNode update;

  private List<ObjectNode> pipeline;

  private List<ObjectNode> arrayFilters;

  private boolean upsert = false;

  private ObjectNode let;

  private ObjectNode hint;

  private boolean validate = true;

  private CollationRecord collation;

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

  public boolean isUpsert() {
    return upsert;
  }

  public void setUpsert(boolean upsert) {
    this.upsert = upsert;
  }

  @Nullable
  public ObjectNode getLet() {
    return let;
  }

  public void setLet(@Nullable ObjectNode let) {
    this.let = let;
  }

  @Nullable
  public ObjectNode getHint() {
    return hint;
  }

  public void setHint(@Nullable ObjectNode hint) {
    this.hint = hint;
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

  @Nullable
  public CollationRecord getCollation() {
    return collation;
  }

  public void setCollation(@Nullable CollationRecord collation) {
    this.collation = collation;
  }
}
