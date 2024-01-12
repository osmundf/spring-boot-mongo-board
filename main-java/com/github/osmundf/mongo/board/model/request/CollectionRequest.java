package com.github.osmundf.mongo.board.model.request;

import org.springframework.lang.Nullable;

public class CollectionRequest extends DatabaseRequest {

  private String collection;

  /**
   * Getter for collection.
   *
   * @return collection name
   */
  @Nullable
  public String getCollection() {
    return collection;
  }

  /**
   * Setter for collection.
   *
   * @param collection collection name
   */
  public void setCollection(@Nullable String collection) {
    this.collection = collection;
  }
}
