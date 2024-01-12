package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class FindOneAndReplaceRequest extends FindRequest {

  private ObjectNode document;

  private String returnUpdate = "after";

  private boolean upsert = false;

  private boolean validate = true;

  public ObjectNode getDocument() {
    return document;
  }

  public void setDocument(ObjectNode document) {
    this.document = document;
  }

  public String getReturnUpdate() {
    return returnUpdate;
  }

  public void setReturnUpdate(String returnUpdate) {
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
