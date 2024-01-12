package com.github.osmundf.mongo.board.view.result;

public class DeleteResultModel {

  private long deleteCount;

  private boolean acknowledged;

  public long getDeleteCount() {
    return deleteCount;
  }

  public void setDeleteCount(long deleteCount) {
    this.deleteCount = deleteCount;
  }

  public boolean isAcknowledged() {
    return acknowledged;
  }

  public void setAcknowledged(boolean acknowledged) {
    this.acknowledged = acknowledged;
  }
}
