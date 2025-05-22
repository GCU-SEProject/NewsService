package com.example.newsservice.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.newsservice.config.NaverApiConfig;
import com.example.newsservice.entity.NewsTag;
import com.example.newsservice.vo.NewsSearchRequest;
import com.example.newsservice.vo.NewsSearchResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NaverApiConfig naverApiConfig;
    private final RestTemplate restTemplate;

    public NewsService(NaverApiConfig naverApiConfig) {
        this.naverApiConfig = naverApiConfig;
        this.restTemplate = new RestTemplate();
    }

    private String buildQueryWithTags(String query, List<NewsTag> tags) {
        if (tags == null || tags.isEmpty()) {
            return query;
        }
        String tagQuery = tags.stream()
                .map(NewsTag::getDescription)
                .collect(Collectors.joining(" "));
        return String.format("%s %s", query, tagQuery);
    }

    public NewsSearchResponse searchNews(NewsSearchRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverApiConfig.getClientId());
        headers.set("X-Naver-Client-Secret", naverApiConfig.getClientSecret());

        String query = buildQueryWithTags(request.getQuery(), request.getTags());

        String url = UriComponentsBuilder
                .fromHttpUrl(naverApiConfig.getBaseUrl() + "/v1/search/news.json")
                .queryParam("query", query)
                .queryParam("display", request.getDisplay())
                .queryParam("start", request.getStart())
                .queryParam("sort", request.getSort())
                .build()
                .toUriString();

        ResponseEntity<NewsSearchResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                NewsSearchResponse.class
        );

        NewsSearchResponse newsSearchResponse = response.getBody();
        if (newsSearchResponse != null && newsSearchResponse.getItems() != null) {
            // 응답의 각 뉴스 항목에 태그들 설정
            newsSearchResponse.getItems().forEach(news -> news.setTags(request.getTags()));
        }

        return newsSearchResponse;
    }
}