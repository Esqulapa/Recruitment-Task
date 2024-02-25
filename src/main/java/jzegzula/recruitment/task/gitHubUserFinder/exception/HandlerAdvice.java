package jzegzula.recruitment.task.gitHubUserFinder.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerAdvice {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
    Map<String, Object> response = new LinkedHashMap<>();
    response.put("status", ex.getStatus());
    response.put("message", ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }
}
