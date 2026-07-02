package com.praj.newsaggregator.article.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ArticleResponse {

    private Long id;

    private String title;

    private String description;

    private String author;

    private String url;

    private String imageUrl;

    private String category;

    private String source;

    private LocalDateTime publishedAt;

}