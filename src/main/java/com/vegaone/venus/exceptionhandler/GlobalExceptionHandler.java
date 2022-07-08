package com.vegaone.venus.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<ErrorCode> handleConflict(EntityNotFoundException ex, WebRequest request) {
        ErrorCode errorCode = new ErrorCode("1404", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorCode);
    }
}
