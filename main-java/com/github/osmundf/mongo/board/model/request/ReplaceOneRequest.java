package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.data.CollationRecord;
import org.springframework.lang.Nullable;

public class ReplaceOneRequest extends FilterRequest {

  private ObjectNode document;

  private boolean upsert = false;

  private ObjectNode let;

  private ObjectNode hint;

  private CollationRecord collation;

  private boolean validate = true;

  @Nullable
  public ObjectNode getDocument() {
    return document;
  }

  public void setDocument(@Nullable ObjectNode document) {
    this.document = document;
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

  @Nullable
  public CollationRecord getCollation() {
    return collation;
  }

  public void setCollation(@Nullable CollationRecord collation) {
    this.collation = collation;
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
