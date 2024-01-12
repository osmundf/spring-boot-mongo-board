package com.github.osmundf.mongo.board.model.bulk;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.CollectionRequest;
import java.util.List;
import java.util.Map;
import org.springframework.lang.Nullable;

public class BulkWriteRequest extends CollectionRequest {

  private List<Map<String, ObjectNode>> requests;

  private ObjectNode let;

  private boolean validate = true;

  private boolean ordered = true;

  public List<Map<String, ObjectNode>> getRequests() {
    return requests;
  }

  public void setRequests(List<Map<String, ObjectNode>> requests) {
    this.requests = requests;
  }

  @Nullable
  public ObjectNode getLet() {
    return let;
  }

  public void setLet(@Nullable ObjectNode let) {
    this.let = let;
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
