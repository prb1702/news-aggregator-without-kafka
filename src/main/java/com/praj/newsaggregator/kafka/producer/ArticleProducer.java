package com.praj.newsaggregator.kafka.producer;

import com.praj.newsaggregator.kafka.KafkaTopics;
import com.praj.newsaggregator.rss.dto.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleProducer {

    private final KafkaTemplate<String, Item> kafkaTemplate;

    public void send(Item item) {
        kafkaTemplate.send(KafkaTopics.ARTICLE_INGEST, item.getLink(), item);
    }
}