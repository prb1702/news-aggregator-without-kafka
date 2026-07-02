package com.praj.newsaggregator.article.entity;

import com.praj.newsaggregator.common.entity.BaseEntity;
import com.praj.newsaggregator.source.entity.Source;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "articles")
public class Article extends BaseEntity {

    @Column(nullable = false, length = 1000)
    private String title;

    @Column(length = 5000)
    private String description;

    @Lob  //TEXT -> PostgreSQL's TEXT type can store very large amounts of text (up to about 1 GB per field).
    private String content;

    private String author;

    private String url;

    private String imageUrl;

    private String category;

    @Column(unique = true)
    private String hash;

    private LocalDateTime publishedAt;


//    Many articles belong to one source.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id")
    private Source source;

}