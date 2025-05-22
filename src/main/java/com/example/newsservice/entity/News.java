package com.example.newsservice.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.example.newsservice.util.CustomLocalDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private String title;
    
    @JsonProperty("description")
    private String description;
    
    private String originallink;
    private String link;
    
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime pubDate;
    private List<NewsTag> tags;
}