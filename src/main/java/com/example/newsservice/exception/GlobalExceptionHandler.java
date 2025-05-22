package com.example.newsservice.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidSearchKeywordException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidSearchKeywordException(InvalidSearchKeywordException e) {
        Map<String, Object> body = createErrorResponse(
            HttpStatus.BAD_REQUEST,
            "Invalid Search Keyword",
            e.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NaverApiClientException.class)
    public ResponseEntity<Map<String, Object>> handleNaverApiClientException(NaverApiClientException e) {
        Map<String, Object> body = createErrorResponse(
            HttpStatus.valueOf(e.getStatusCode()),
            "Naver API Error",
            e.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.valueOf(e.getStatusCode()));
    }

    @ExceptionHandler(NewsApiException.class)
    public ResponseEntity<Map<String, Object>> handleNewsApiException(NewsApiException e) {
        Map<String, Object> body = createErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "News API Error",
            e.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> createErrorResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        return body;
    }
} 