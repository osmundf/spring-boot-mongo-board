package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.data.CollationRecord;
import org.springframework.lang.Nullable;

public class CreateIndexRequest extends CollectionRequest {

  private ObjectNode keys;

  private String defaultLanguage;

  private Integer bits;

  private boolean background = false;

  private Long expireAfter;

  private CollationRecord collation;

  @Nullable
  public ObjectNode getKeys() {
    return keys;
  }

  public void setKeys(@Nullable ObjectNode keys) {
    this.keys = keys;
  }

  @Nullable
  public String getDefaultLanguage() {
    return defaultLanguage;
  }

  public void setDefaultLanguage(@Nullable String defaultLanguage) {
    this.defaultLanguage = defaultLanguage;
  }

  @Nullable
  public Integer getBits() {
    return bits;
  }

  public void setBits(@Nullable Integer bits) {
    this.bits = bits;
  }

  public boolean isBackground() {
    return background;
  }

  public void setBackground(boolean background) {
    this.background = background;
  }

  @Nullable
  public Long getExpireAfter() {
    return expireAfter;
  }

  public void setExpireAfter(@Nullable Long expireAfter) {
    this.expireAfter = expireAfter;
  }

  @Nullable
  public CollationRecord getCollation() {
    return collation;
  }

  public void setCollation(@Nullable CollationRecord collation) {
    this.collation = collation;
  }
}
