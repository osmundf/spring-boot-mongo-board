package com.github.osmundf.mongo.board.model.request;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.lang.Nullable;

public class DatabaseRequest implements Serializable {

  private String database;

  private String readPreference;

  private List<Map<String, String>> tagList;

  private Long maxStaleness;

  private String readConcern;

  private String writeConcern;

  /**
   * Getter for database.
   *
   * @return database name
   */
  @Nullable
  public String getDatabase() {
    return database;
  }

  /**
   * Setter for database.
   *
   * @param database database name
   */
  public void setDatabase(@Nullable String database) {
    this.database = database;
  }

  public String getReadPreference() {
    return readPreference;
  }

  public void setReadPreference(String readPreference) {
    this.readPreference = readPreference;
  }

  @Nullable
  public List<Map<String, String>> getTagList() {
    return tagList;
  }

  public void setTagList(@Nullable List<Map<String, String>> tagList) {
    this.tagList = tagList;
  }

  @Nullable
  public Long getMaxStaleness() {
    return maxStaleness;
  }

  public void setMaxStaleness(@Nullable Long maxStaleness) {
    this.maxStaleness = maxStaleness;
  }

  @Nullable
  public String getReadConcern() {
    return readConcern;
  }

  public void setReadConcern(@Nullable String readConcern) {
    this.readConcern = readConcern;
  }

  @Nullable
  public String getWriteConcern() {
    return writeConcern;
  }

  public void setWriteConcern(@Nullable String writeConcern) {
    this.writeConcern = writeConcern;
  }
}
