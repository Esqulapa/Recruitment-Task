package jzegzula.recruitment.task.gitHubUserFinder.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class HandlerAdvice {

  @ExceptionHandler(HttpClientErrorException.NotFound.class)
  public ResponseEntity<Map<String, Object>> handleUserNotFoundException(
      HttpClientErrorException.NotFound ex) {
    Map<String, Object> response = new LinkedHashMap<>();
    response.put("status", ex.getStatusCode().value());
    response.put("message", "Username not found.");
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }
}
