package com.github.osmundf.mongo.board.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.springframework.lang.Nullable;

/**
 * Insert Many Request.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class InsertManyRequest extends CollectionRequest {

  private List<ObjectNode> documents;

  private boolean validate = true;

  private boolean ordered = true;

  /**
   * Getter for documents.
   *
   * @return list of object nodes for documents
   */
  @Nullable
  public List<ObjectNode> getDocuments() {
    return documents;
  }

  /**
   * Setter for documents.
   *
   * @param documents list of object nodes for documents
   */
  public void setDocuments(@Nullable List<ObjectNode> documents) {
    this.documents = documents;
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
