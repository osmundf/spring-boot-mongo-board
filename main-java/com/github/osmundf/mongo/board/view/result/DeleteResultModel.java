package com.github.osmundf.mongo.board.view.result;

/**
 * Delete Result Model.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class DeleteResultModel {

  private long deleteCount;

  private boolean acknowledged;

  /**
   * Getter for delete count.
   *
   * @return a long
   */
  public long getDeleteCount() {
    return deleteCount;
  }

  /**
   * Setter for delete count.
   *
   * @param deleteCount a long
   */
  public void setDeleteCount(long deleteCount) {
    this.deleteCount = deleteCount;
  }

  /**
   * Getter for acknowledged.
   *
   * @return true if acknowledged, false otherwise
   */
  public boolean isAcknowledged() {
    return acknowledged;
  }

  /**
   * Setter for acknowledged.
   *
   * @param acknowledged acknowledged
   */
  public void setAcknowledged(boolean acknowledged) {
    this.acknowledged = acknowledged;
  }
}
