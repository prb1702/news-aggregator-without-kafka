package com.praj.newsaggregator.rss.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.praj.newsaggregator.rss.dto.Item;
import com.praj.newsaggregator.rss.dto.RssFeed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RssParser {

    private final HttpClient httpClient;

    private final XmlMapper xmlMapper = new XmlMapper();
    {
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<Item> parse(String rssUrl) {

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(rssUrl))
                    .GET()
                    .build();

            HttpResponse<InputStream> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            RssFeed rssFeed = xmlMapper.readValue(response.body(), RssFeed.class);

            return rssFeed.getChannel().getItems();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to parse RSS Feed", e);
        }
    }
}