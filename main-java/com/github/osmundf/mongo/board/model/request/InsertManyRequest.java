package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.springframework.lang.Nullable;

public class InsertManyRequest extends CollectionRequest {

  private List<ObjectNode> documents;

  private boolean validate = true;

  private boolean ordered = true;

  @Nullable
  public List<ObjectNode> getDocuments() {
    return documents;
  }

  public void setDocuments(@Nullable List<ObjectNode> documents) {
    this.documents = documents;
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

  public boolean isOrdered() {
    return ordered;
  }

  public void setOrdered(boolean ordered) {
    this.ordered = ordered;
  }
}
