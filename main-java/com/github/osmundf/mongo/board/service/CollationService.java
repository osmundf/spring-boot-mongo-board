package com.github.osmundf.mongo.board.service;

import com.github.osmundf.mongo.board.model.data.CollationRecord;
import com.mongodb.client.model.Collation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class CollationService {

  @Nullable
  public Collation toCollation(@Nullable CollationRecord collationRecord) {
    return collationRecord != null ? collationRecord.toMongoCollation() : null;
  }
}
