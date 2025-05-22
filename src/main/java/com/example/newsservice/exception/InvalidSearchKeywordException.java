package com.example.newsservice.exception;

public class InvalidSearchKeywordException extends NewsApiException {
    public InvalidSearchKeywordException(String message) {
        super(message);
    }
} 