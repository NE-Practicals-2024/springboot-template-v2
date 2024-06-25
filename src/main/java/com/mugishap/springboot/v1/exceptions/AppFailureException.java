package com.mugishap.springboot.v1.exceptions;


import com.mugishap.springboot.v1.payload.ApiResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class AppFailureException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleAnyError(RuntimeException exception) {
        return ResponseEntity.badRequest().body(new ApiResponse(false, exception.getMessage(), exception));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidations(MethodArgumentNotValidException exception) {
        FieldError error = Objects.requireNonNull(exception.getFieldError());
        String message = error.getField() + ": " + error.getDefaultMessage();
        return ResponseEntity.badRequest().body(new ApiResponse(false, message, error));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleSqlExceptions(ConstraintViolationException exception) {
        return ResponseEntity.badRequest().body(new ApiResponse(false, exception.getMessage() + " - " + exception.getSQL() + " - " + exception.getSQLState(), exception.getSQLException()));
    }
}
