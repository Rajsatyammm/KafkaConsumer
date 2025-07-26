package com.hcl.kafka_consumer.handlers;

import com.hcl.kafka_consumer.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleAllOtherException(Exception e) {
        ApiResponse response = ApiResponse.builder()
            .message(e.getMessage())
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .success(false)
            .build();
        return ResponseEntity.internalServerError().body(response);
    }
}
