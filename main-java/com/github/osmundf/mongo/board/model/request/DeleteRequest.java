package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.data.CollationRecord;
import org.springframework.lang.Nullable;

public class DeleteRequest extends FilterRequest {

  private ObjectNode let;

  private ObjectNode hint;

  private CollationRecord collation;

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
}
