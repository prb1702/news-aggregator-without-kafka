package com.praj.newsaggregator.article.repository;

import com.praj.newsaggregator.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByHash(String hash);

    List<Article> findByCategoryIgnoreCase(String category);

    List<Article> findTop20ByOrderByPublishedAtDesc();

}