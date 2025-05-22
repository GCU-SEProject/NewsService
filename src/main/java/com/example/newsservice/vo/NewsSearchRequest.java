package com.example.newsservice.vo;

import java.util.List;

import com.example.newsservice.entity.NewsTag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsSearchRequest {
    private String query;
    private List<NewsTag> tags;
    private Integer display;
    private Integer start;
    private String sort;
}