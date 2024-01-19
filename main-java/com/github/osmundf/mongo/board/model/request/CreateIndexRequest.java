package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.data.CollationRecord;
import org.springframework.lang.Nullable;

/**
 * Create Index Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class CreateIndexRequest extends CollectionRequest {

  private ObjectNode keys;

  private String defaultLanguage;

  private Integer bits;

  private boolean background = false;

  private Long expireAfter;

  private CollationRecord collation;

  /**
   * Getter for keys.
   *
   * @return object node for keys
   */
  @Nullable
  public ObjectNode getKeys() {
    return keys;
  }

  /**
   * Setter for keys.
   *
   * @param keys object node for keys
   */
  public void setKeys(@Nullable ObjectNode keys) {
    this.keys = keys;
  }

  /**
   * Getter for default language.
   *
   * @return default language value
   */
  @Nullable
  public String getDefaultLanguage() {
    return defaultLanguage;
  }

  /**
   * Setter for default language.
   *
   * @param defaultLanguage default language value
   */
  public void setDefaultLanguage(@Nullable String defaultLanguage) {
    this.defaultLanguage = defaultLanguage;
  }

  /**
   * Getter for bits.
   *
   * @return bits
   */
  @Nullable
  public Integer getBits() {
    return bits;
  }

  /**
   * Setter for bits.
   *
   * @param bits bits
   */
  public void setBits(@Nullable Integer bits) {
    this.bits = bits;
  }

  /**
   * Getter for background.
   *
   * @return true if creation is in background, false otherwise
   */
  public boolean isBackground() {
    return background;
  }

  /**
   * Setter for background.
   *
   * @param background background
   */
  public void setBackground(boolean background) {
    this.background = background;
  }

  /**
   * Getter for expire after.
   *
   * @return expire after
   */
  @Nullable
  public Long getExpireAfter() {
    return expireAfter;
  }

  /**
   * Setter for expire after.
   *
   * @param expireAfter expire after
   */
  public void setExpireAfter(@Nullable Long expireAfter) {
    this.expireAfter = expireAfter;
  }

  /**
   * Getter for collation.
   *
   * @return collation record
   */
  @Nullable
  public CollationRecord getCollation() {
    return collation;
  }

  /**
   * Setter for collation.
   *
   * @param collation collation record
   */
  public void setCollation(@Nullable CollationRecord collation) {
    this.collation = collation;
  }
}
