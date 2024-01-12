package com.github.osmundf.mongo.board.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.github.osmundf.mongo.board.view.error.ErrorMessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

/**
 * Response Status Exception Handler.
 *
 * @author Osmund
 * @version $Id: $Id
 */
@ControllerAdvice
public class BoardControllerAdvice {

  private static final Logger LOGGER = LoggerFactory.getLogger(BoardControllerAdvice.class);

  /**
   * Exception handler.
   *
   * @param exception exception
   * @param webRequest web request
   * @return response entity with error model
   */
  @ExceptionHandler(RuntimeException.class)
  public final ResponseEntity<ErrorMessageModel> handleBadRequestException(
      RuntimeException exception, WebRequest webRequest) {
    LOGGER.error(exception.getMessage(), exception);
    return buildResponse(INTERNAL_SERVER_ERROR, exception, webRequest);
  }

  /**
   * Exception handler.
   *
   * @param exception exception
   * @param webRequest web request
   * @return response entity with error model
   */
  @ExceptionHandler(ResponseStatusException.class)
  public final ResponseEntity<ErrorMessageModel> handleBadRequestException(
      ResponseStatusException exception, WebRequest webRequest) {
    final var code = exception.getStatusCode();
    return buildResponse(code, exception, webRequest);
  }

  /**
   * Exception handler.
   *
   * @param exception exception
   * @param webRequest web request
   * @return response entity with error model
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public final ResponseEntity<ErrorMessageModel> handleIllegalArgumentException(
      IllegalArgumentException exception, WebRequest webRequest) {
    return buildResponse(BAD_REQUEST, exception, webRequest);
  }

  /**
   * Private helper method for building response entity with error message model.
   *
   * @param code HTTP status code
   * @param exception exception
   * @param webRequest web request
   * @return response entity with error message model
   */
  private ResponseEntity<ErrorMessageModel> buildResponse(
      HttpStatusCode code, Exception exception, WebRequest webRequest) {
    final var path = ((ServletWebRequest) webRequest).getRequest().getRequestURI();
    final var message = exception.getMessage();
    final var cause = exception.getCause() != null ? exception.getCause().getMessage() : null;
    final var model = new ErrorMessageModel(code, path, message, cause);
    return new ResponseEntity<>(model, code);
  }
}
