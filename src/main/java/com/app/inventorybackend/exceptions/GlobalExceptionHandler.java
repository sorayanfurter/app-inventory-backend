package com.app.inventorybackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailNotFoundException.class)

    public ResponseEntity<ErrorDetails> handleEmailNotFoundException(EmailNotFoundException ex) {

        ErrorDetails error = ErrorDetails.builder().status("404").message(ex.getMessage()).build();

        return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);

    }
}