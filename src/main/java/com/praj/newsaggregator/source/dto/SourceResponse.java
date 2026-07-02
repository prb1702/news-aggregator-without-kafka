package com.praj.newsaggregator.source.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SourceResponse {

    private Long id;
    private String name;
    private String rssUrl;
    private String category;
    private boolean enabled;

}