package com.rb.alwaysontheroad.rawmetricsconsumer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /* Catch bean validation exception */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBeenValidationException(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return buildResponse(errors.toString(), HttpStatus.BAD_REQUEST);
    }

    /* Catch others */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOthersException(Exception ex) {
        log.error("Exception has occurred: ", ex);
        return buildResponse(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildResponse(String body, HttpStatus status) {
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .message(body)
                        .time(Instant.now()).build()
                );
    }
}
