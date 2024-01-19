package com.github.osmundf.mongo.board.view.result;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;

/**
 * Bulk Write Result Model.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class BulkWriteResultModel {

  private Map<Integer, JsonNode> inserts;

  private Map<Integer, JsonNode> upserts;

  private int matchedCount;

  private int insertedCount;

  private int modifiedCount;

  private int deletedCount;

  /**
   * Getter for inserts.
   *
   * @return map of index to insert Ids
   */
  public Map<Integer, JsonNode> getInserts() {
    return inserts;
  }

  /**
   * Setter for inserts.
   *
   * @param inserts map of index to insert Ids
   */
  public void setInserts(Map<Integer, JsonNode> inserts) {
    this.inserts = inserts;
  }

  /**
   * Getter for upserts.
   *
   * @return a map of index to upsert Id
   */
  public Map<Integer, JsonNode> getUpserts() {
    return upserts;
  }

  /**
   * Setter for upserts.
   *
   * @param upserts a map of index to upsert Id
   */
  public void setUpserts(Map<Integer, JsonNode> upserts) {
    this.upserts = upserts;
  }

  /**
   * Getter for matched count.
   *
   * @return bulk write matched count
   */
  public int getMatchedCount() {
    return matchedCount;
  }

  /**
   * Setter for matched count.
   *
   * @param matchedCount bulk write matched count
   */
  public void setMatchedCount(int matchedCount) {
    this.matchedCount = matchedCount;
  }

  /**
   * Getter for inserted count.
   *
   * @return bulk write inserted count
   */
  public int getInsertedCount() {
    return insertedCount;
  }

  /**
   * Setter for inserted count.
   *
   * @param insertedCount bulk write inserted count
   */
  public void setInsertedCount(int insertedCount) {
    this.insertedCount = insertedCount;
  }

  /**
   * Getter for modified count.
   *
   * @return bulk write modified count
   */
  public int getModifiedCount() {
    return modifiedCount;
  }

  /**
   * Setter for modified count.
   *
   * @param modifiedCount bulk write modified count
   */
  public void setModifiedCount(int modifiedCount) {
    this.modifiedCount = modifiedCount;
  }

  /**
   * Getter for deleted count.
   *
   * @return bulk write deleted count
   */
  public int getDeletedCount() {
    return deletedCount;
  }

  /**
   * Setter for deleted count.
   *
   * @param deletedCount bulk write deleted count
   */
  public void setDeletedCount(int deletedCount) {
    this.deletedCount = deletedCount;
  }
}
