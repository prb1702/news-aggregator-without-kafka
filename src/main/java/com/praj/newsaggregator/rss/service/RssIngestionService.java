package com.praj.newsaggregator.rss.service;

import com.praj.newsaggregator.article.entity.Article;
import com.praj.newsaggregator.article.repository.ArticleRepository;
import com.praj.newsaggregator.article.util.ArticleHashUtil;
import com.praj.newsaggregator.kafka.producer.ArticleProducer;
import com.praj.newsaggregator.rss.dto.Item;
import com.praj.newsaggregator.rss.parser.RssParser;
import com.praj.newsaggregator.source.entity.Source;
import com.praj.newsaggregator.source.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RssIngestionService {

    private final RssParser rssParser;
    private final ArticleRepository articleRepository;
    private final SourceRepository sourceRepository;
    private final ArticleProducer articleProducer;

    public int sync(Long sourceId) {

        Source source = sourceRepository.findById(sourceId)
                .orElseThrow(() -> new RuntimeException("Source not found"));

        List<Item> items = rssParser.parse(source.getRssUrl());

        int count = 0;

        for (Item item : items) {
            articleProducer.send(item);
            count++;
        }

        return count;
    }
//    @Transactional
//    public int sync(Long sourceId) {
//
//        Source source = sourceRepository.findById(sourceId)
//                .orElseThrow(() -> new RuntimeException("Source not found: " + sourceId));
//
//        List<Item> items = rssParser.parse(source.getRssUrl());
//
//        if (items == null || items.isEmpty()) {
//            return 0;
//        }
//
//        // Fetch existing hashes once (avoid N DB calls)
//        Set<String> existingHashes = new HashSet<>(
//                articleRepository.findAll()
//                        .stream()
//                        .map(Article::getHash)
//                        .filter(Objects::nonNull)
//                        .toList()
//        );
//
//        List<Article> articlesToSave = new ArrayList<>();
//
//        int savedCount = 0;
//
//        for (Item item : items) {
//
//            if (item == null || item.getTitle() == null || item.getLink() == null) {
//                continue;
//            }
//
//            String hash = ArticleHashUtil.generate(item.getTitle(), item.getLink());
//
//            if (existingHashes.contains(hash)) {
//                continue;
//            }
////            if (articleRepository.existsByUrl(item.getLink())) {
////                continue; // skip duplicates
////            }
//
//            Article article = new Article();
//            article.setTitle(item.getTitle());
//            article.setDescription(item.getDescription());
//            article.setUrl(item.getLink());
//            article.setAuthor(item.getAuthor());
//            article.setPublishedAt(parseDate(item.getPubDate()));
//            article.setSource(source);
//            article.setHash(hash);
//
//            articlesToSave.add(article);
//            existingHashes.add(hash); // prevent duplicates within same batch
//            savedCount++;
//        }
//        if (!articlesToSave.isEmpty()) {
//            articleRepository.saveAll(articlesToSave);
//        }
//
//        return savedCount;
//    }

    private LocalDateTime parseDate(String pubDate) {
        if (pubDate == null || pubDate.isBlank()) {
            return LocalDateTime.now();
        }
        try {
            return ZonedDateTime.parse(pubDate,
                            DateTimeFormatter.RFC_1123_DATE_TIME)
                    .toLocalDateTime();
        } catch (Exception e) {
            return LocalDateTime.now();
        }
    }
}