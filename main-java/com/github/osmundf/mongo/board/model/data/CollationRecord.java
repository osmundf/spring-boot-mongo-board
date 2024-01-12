package com.github.osmundf.mongo.board.model.data;

import com.mongodb.client.model.Collation;
import com.mongodb.client.model.CollationAlternate;
import com.mongodb.client.model.CollationCaseFirst;
import com.mongodb.client.model.CollationMaxVariable;
import com.mongodb.client.model.CollationStrength;
import jakarta.annotation.Nullable;

public record CollationRecord(
    @Nullable String locale,
    @Nullable Boolean caseLevel,
    @Nullable String caseFirst,
    @Nullable Integer strength,
    @Nullable Boolean numericOrdering,
    @Nullable String alternate,
    @Nullable String maxVariable,
    @Nullable Boolean normalization,
    @Nullable Boolean backwards) {

  public Collation toMongoCollation() {
    return Collation.builder()
        .locale(locale)
        .caseLevel(caseLevel)
        .collationCaseFirst(getCaseFirst())
        .collationStrength(getStrength())
        .numericOrdering(numericOrdering)
        .collationAlternate(getAlternate())
        .collationMaxVariable(getMaxVariable())
        .normalization(normalization)
        .backwards(backwards)
        .build();
  }

  private CollationCaseFirst getCaseFirst() {
    return caseFirst != null ? CollationCaseFirst.fromString(caseFirst) : null;
  }

  private CollationStrength getStrength() {
    return strength != null ? CollationStrength.fromInt(strength) : null;
  }

  private CollationAlternate getAlternate() {
    return alternate != null ? CollationAlternate.fromString(alternate) : null;
  }

  private CollationMaxVariable getMaxVariable() {
    return maxVariable != null ? CollationMaxVariable.fromString(maxVariable) : null;
  }
}
