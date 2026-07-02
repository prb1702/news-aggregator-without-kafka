package com.praj.newsaggregator.kafka.consumer;

import com.praj.newsaggregator.article.entity.Article;
import com.praj.newsaggregator.article.repository.ArticleRepository;
import com.praj.newsaggregator.article.util.ArticleHashUtil;
import com.praj.newsaggregator.kafka.KafkaTopics;
import com.praj.newsaggregator.rss.dto.Item;
import com.praj.newsaggregator.source.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleConsumer {

    private final ArticleRepository articleRepository;
    private final SourceRepository sourceRepository;

    @KafkaListener(topics = KafkaTopics.ARTICLE_INGEST, groupId = "news-group")
    public void consume(Item item) {

        String hash = ArticleHashUtil.generate(item.getTitle(), item.getLink());

        if (articleRepository.existsByHash(hash)) {
            return;
        }

        Article article = new Article();
        article.setTitle(item.getTitle());
        article.setDescription(item.getDescription());
        article.setUrl(item.getLink());
        article.setAuthor(item.getAuthor());
        article.setPublishedAt(null);
        article.setHash(hash);

        articleRepository.save(article);
    }
}