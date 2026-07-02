package com.praj.newsaggregator.source.dto;

import lombok.Data;

@Data
public class SourceRequest {

    private String name;

    private String rssUrl;

    private String category;

}