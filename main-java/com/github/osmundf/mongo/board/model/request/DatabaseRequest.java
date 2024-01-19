package com.github.osmundf.mongo.board.model.request;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.lang.Nullable;

/**
 * Database Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
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

  /**
   * Getter for read preference.
   *
   * @return read preference
   */
  @Nullable
  public String getReadPreference() {
    return readPreference;
  }

  /**
   * Setter for read preference.
   *
   * @param readPreference read preference
   */
  public void setReadPreference(String readPreference) {
    this.readPreference = readPreference;
  }

  /**
   * Getter for tag list.
   *
   * @return list of string mappings
   */
  @Nullable
  public List<Map<String, String>> getTagList() {
    return tagList;
  }

  /**
   * Setter for tag list.
   *
   * @param tagList list of string mappings
   */
  public void setTagList(@Nullable List<Map<String, String>> tagList) {
    this.tagList = tagList;
  }

  /**
   * Getter for max staleness.
   *
   * @return max staleness duration in milliseconds
   */
  @Nullable
  public Long getMaxStaleness() {
    return maxStaleness;
  }

  /**
   * Setter for max staleness.
   *
   * @param maxStaleness max staleness duration in milliseconds
   */
  public void setMaxStaleness(@Nullable Long maxStaleness) {
    this.maxStaleness = maxStaleness;
  }

  /**
   * Getter for read concern.
   *
   * @return read concern value
   */
  @Nullable
  public String getReadConcern() {
    return readConcern;
  }

  /**
   * Setter for read concern.
   *
   * @param readConcern read concern value
   */
  public void setReadConcern(@Nullable String readConcern) {
    this.readConcern = readConcern;
  }

  /**
   * Getter for write concern.
   *
   * @return write concern value
   */
  @Nullable
  public String getWriteConcern() {
    return writeConcern;
  }

  /**
   * Setter for write concern.
   *
   * @param writeConcern write concern value
   */
  public void setWriteConcern(@Nullable String writeConcern) {
    this.writeConcern = writeConcern;
  }
}
