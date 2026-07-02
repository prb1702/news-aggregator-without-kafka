package com.praj.newsaggregator.article.repository;

import com.praj.newsaggregator.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @EntityGraph(attributePaths = "source")
    Page<Article> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "source")
    Page<Article> findByCategoryIgnoreCase(String category, Pageable pageable);

    @EntityGraph(attributePaths = "source")
    Page<Article> findBySource_NameIgnoreCase(String sourceName, Pageable pageable);

    @EntityGraph(attributePaths = "source")
    List<Article> findAllByOrderByPublishedAtDesc();

    Optional<Article> findByHash(String hash);

    List<Article> findByCategoryIgnoreCase(String category);

    List<Article> findTop20ByOrderByPublishedAtDesc();

    boolean existsByUrl(String url);

    boolean existsByHash(String hash);

    @Query("SELECT a FROM Article a JOIN FETCH a.source")
    List<Article> findAllWithSource();
}