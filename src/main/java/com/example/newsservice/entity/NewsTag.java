package com.example.newsservice.entity;

public enum NewsTag {
    POLITICS("정치"),
    ECONOMY("경제"),
    SPORTS("스포츠"),
    IT("IT"),
    ENTERTAINMENT("연예");

    private final String description;

    NewsTag(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}