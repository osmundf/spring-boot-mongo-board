package com.github.osmundf.mongo.board.model.bulk;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osmundf.mongo.board.model.request.CollectionRequest;
import java.util.List;
import java.util.Map;
import org.springframework.lang.Nullable;

/**
 * Bulk Write Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class BulkWriteRequest extends CollectionRequest {

  private List<Map<String, ObjectNode>> requests;

  private ObjectNode let;

  private boolean validate = true;

  private boolean ordered = true;

  /**
   * Getter for requests.
   *
   * @return a list of operation to object node map
   */
  public List<Map<String, ObjectNode>> getRequests() {
    return requests;
  }

  /**
   * Setter for requests.
   *
   * @param requests a list of operation to object node map
   */
  public void setRequests(List<Map<String, ObjectNode>> requests) {
    this.requests = requests;
  }

  /**
   * Getter for let.
   *
   * @return object node for let
   */
  @Nullable
  public ObjectNode getLet() {
    return let;
  }

  /**
   * Setter for let.
   *
   * @param let object node for let
   */
  public void setLet(@Nullable ObjectNode let) {
    this.let = let;
  }

  /**
   * Getter for validate.
   *
   * @return true if validated, false otherwise
   */
  public boolean isValidate() {
    return validate;
  }

  /**
   * Setter for validate.
   *
   * @param validate validate
   */
  public void setValidate(boolean validate) {
    this.validate = validate;
  }

  /**
   * Getter for bypassing document validation.
   *
   * @return true if document validation is bypassed, false otherwise
   */
  public boolean bypassDocumentValidation() {
    return !validate;
  }

  /**
   * Getter for ordered.
   *
   * @return true if ordered, false otherwise
   */
  public boolean isOrdered() {
    return ordered;
  }

  /**
   * Setter for ordered.
   *
   * @param ordered ordered
   */
  public void setOrdered(boolean ordered) {
    this.ordered = ordered;
  }
}
