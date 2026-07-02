package com.praj.newsaggregator.rss.controller;

import com.praj.newsaggregator.rss.dto.Item;
import com.praj.newsaggregator.rss.parser.RssParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rss")
@RequiredArgsConstructor
public class RssTestController {

    private final RssParser rssParser;

    @GetMapping("/test")
    public List<Item> test() {

        return rssParser.parse(
                "https://feeds.bbci.co.uk/news/rss.xml"
        );

    }

}