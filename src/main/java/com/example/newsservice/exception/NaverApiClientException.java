package com.example.newsservice.exception;

public class NaverApiClientException extends NewsApiException {
    private final int statusCode;

    public NaverApiClientException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public NaverApiClientException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
} 