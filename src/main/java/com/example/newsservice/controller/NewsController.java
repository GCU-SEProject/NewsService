package com.example.newsservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.newsservice.entity.NewsTag;
import com.example.newsservice.service.NewsService;
import com.example.newsservice.vo.NewsSearchRequest;
import com.example.newsservice.vo.NewsSearchResponse;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/search")
    public ResponseEntity<NewsSearchResponse> searchNews(
            @RequestParam String keyword,
            @RequestParam(required = false) List<NewsTag> tags,
            @RequestParam(required = false, defaultValue = "10") Integer display,
            @RequestParam(required = false, defaultValue = "1") Integer start,
            @RequestParam(required = false, defaultValue = "sim") String sort
    ) {
        NewsSearchRequest request = NewsSearchRequest.builder()
                .query(keyword)
                .tags(tags)
                .display(display)
                .start(start)
                .sort(sort)
                .build();

        return ResponseEntity.ok(newsService.searchNews(request));
    }
}