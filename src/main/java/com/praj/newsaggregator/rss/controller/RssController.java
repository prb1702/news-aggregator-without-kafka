package com.praj.newsaggregator.rss.controller;

import com.praj.newsaggregator.rss.service.RssIngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/rss")
@RequiredArgsConstructor
public class RssController {

    private final RssIngestionService ingestionService;

    @PostMapping("/sync/{sourceId}")
    public Map<String, Object> sync(@PathVariable Long sourceId) {

        int count = ingestionService.sync(sourceId);

        return Map.of(
                "success", true,
                "articlesSaved", count
        );
    }
}