package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.lang.Nullable;

public class InsertOneRequest extends CollectionRequest {

  private ObjectNode document;

  private boolean validate = true;

  @Nullable
  public ObjectNode getDocument() {
    return document;
  }

  public void setDocument(@Nullable ObjectNode document) {
    this.document = document;
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
