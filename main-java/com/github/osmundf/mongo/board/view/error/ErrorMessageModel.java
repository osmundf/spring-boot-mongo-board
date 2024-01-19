package com.github.osmundf.mongo.board.view.error;

import static java.time.ZoneOffset.UTC;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;
import org.springframework.http.HttpStatusCode;

/**
 * Error Message Model.
 *
 * <p>Error message model facilities for API error message.
 *
 * @author osmundf
 * @version $Id: $Id
 */
public class ErrorMessageModel implements Serializable {

  @JsonProperty("timestamp")
  private final OffsetDateTime dateTime;

  @JsonProperty("code")
  private final int code;

  @JsonProperty("error")
  private final HttpStatusCode error;

  @JsonProperty("path")
  private final String path;

  @JsonProperty("message")
  private final String message;

  @JsonProperty("cause")
  @JsonInclude(value = Include.NON_NULL)
  private final String cause;

  /**
   * Error message model.
   *
   * @param code HTTP status code
   * @param path path
   * @param message error message
   * @param cause error cause
   */
  @JsonCreator
  public ErrorMessageModel(HttpStatusCode code, String path, String message, String cause) {
    this.dateTime = Instant.now().atOffset(UTC);
    this.code = code.value();
    this.error = code;
    this.path = path;
    this.message = message;
    this.cause = cause;
  }

  /**
   * Getter for date-time.
   *
   * @return offset date-time
   */
  public OffsetDateTime getDateTime() {
    return dateTime;
  }

  /**
   * Getter for code.
   *
   * @return HTTP status code integer value
   */
  public int getCode() {
    return code;
  }

  /**
   * Getter for error.
   *
   * @return HTTP status code for error
   */
  public HttpStatusCode getError() {
    return error;
  }

  /**
   * Getter for path.
   *
   * @return path
   */
  public String getPath() {
    return path;
  }

  /**
   * Getter for message.
   *
   * @return message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Getter for cause.
   *
   * @return cause
   */
  public String getCause() {
    return cause;
  }
}
