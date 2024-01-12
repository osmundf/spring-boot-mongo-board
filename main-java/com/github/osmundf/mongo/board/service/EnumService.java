package com.github.osmundf.mongo.board.service;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.Tag;
import com.mongodb.TagSet;
import com.mongodb.WriteConcern;
import com.mongodb.client.model.ReturnDocument;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EnumService {

  @NonNull
  public ReturnDocument getReturnDocument(@Nullable String value) {
    if (value == null) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Could not find return document value.");
    }
    return switch (value) {
      case "after" -> ReturnDocument.AFTER;
      case "before" -> ReturnDocument.BEFORE;
      default -> throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid return document value: " + value);
    };
  }

  @NonNull
  public ReadPreference getReadPreference(
      @NonNull String value,
      @Nullable List<Map<String, String>> tagSets,
      @Nullable Long maxStaleness) {
    var preference =
        switch (value) {
          case "primary" -> ReadPreference.primary();
          case "primaryPreferred" -> ReadPreference.primaryPreferred();
          case "secondary" -> ReadPreference.secondary();
          case "secondaryPreferred" -> ReadPreference.secondaryPreferred();
          case "nearest" -> ReadPreference.nearest();
          default -> throw new ResponseStatusException(
              HttpStatus.BAD_REQUEST, "Invalid read preference value: " + value);
        };

    if (tagSets != null) {
      preference = preference.withTagSetList(toTagSetList(tagSets));
    }

    if (maxStaleness != null) {
      preference = preference.withMaxStalenessMS(maxStaleness, MILLISECONDS);
    }

    return preference;
  }

  @NonNull
  public ReadConcern getReadConcern(@NonNull String value) {
    return switch (value) {
      case "available" -> ReadConcern.AVAILABLE;
      case "default" -> ReadConcern.DEFAULT;
      case "linearizable" -> ReadConcern.LINEARIZABLE;
      case "local" -> ReadConcern.LOCAL;
      case "majority" -> ReadConcern.MAJORITY;
      case "snapshot" -> ReadConcern.SNAPSHOT;
      default -> throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid read concern value: " + value);
    };
  }

  @NonNull
  public WriteConcern getWriteConcern(@NonNull String value) {
    return switch (value) {
      case "0", "unacknowledged" -> WriteConcern.UNACKNOWLEDGED;
      case "1" -> WriteConcern.W1;
      case "2" -> WriteConcern.W2;
      case "3" -> WriteConcern.W3;
      case "acknowledged" -> WriteConcern.ACKNOWLEDGED;
      case "journaled" -> WriteConcern.JOURNALED;
      case "majority" -> WriteConcern.MAJORITY;
      default -> throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid read concern value: " + value);
    };
  }

  private List<TagSet> toTagSetList(List<Map<String, String>> input) {
    final var result = new ArrayList<TagSet>();
    for (final var item : input) {
      result.add(toTagSet(item));
    }
    return result;
  }

  private TagSet toTagSet(Map<String, String> input) {
    final var list = new ArrayList<Tag>(input.size());
    for (final var item : input.entrySet()) {
      list.add(new Tag(item.getKey(), item.getValue()));
    }
    return new TagSet(list);
  }
}
