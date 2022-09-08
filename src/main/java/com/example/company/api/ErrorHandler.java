package com.example.company.api;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.example.company.domain.exception.CacheProcessorException;
import com.example.company.domain.exception.DomainException;
import com.example.company.domain.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
    var errorResponse = ErrorResponse.create(100, e.getMessage());

    return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorResponse);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
    var errorResponse = ErrorResponse.create(101, e.getMessage());

    return ResponseEntity.status(NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(DomainException.class)
  public ResponseEntity<ErrorResponse> handleDomainException(DomainException e) {
    var errorResponse = ErrorResponse.create(102, e.getMessage());

    return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
  }

  @ExceptionHandler(CacheProcessorException.class)
  public ResponseEntity<ErrorResponse> handleCacheProcessorException(CacheProcessorException e) {
    var errorResponse = ErrorResponse.create(103, e.getMessage());

    return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorResponse);
  }
}
