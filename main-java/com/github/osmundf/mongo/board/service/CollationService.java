package com.github.osmundf.mongo.board.service;

import com.github.osmundf.mongo.board.model.data.CollationRecord;
import com.mongodb.client.model.Collation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 * Collation Service.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@Service
public class CollationService {

  /**
   * Return a collation for a given collation record.
   *
   * @param collationRecord collation record
   * @return collation for collation record
   */
  @Nullable
  public Collation toCollation(@Nullable CollationRecord collationRecord) {
    return collationRecord != null ? collationRecord.toMongoCollation() : null;
  }
}
