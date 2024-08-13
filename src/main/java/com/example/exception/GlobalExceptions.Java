package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZonedDateTime;
import org.springframework.web.HttpRequestMethodNotSupportedException;



@ControllerAdvice
public class HandleExceptions{

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionBody> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        ExceptionBody exceptionBody = new ExceptionBody(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED, ZonedDateTime.now());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(exceptionBody);
    }

}