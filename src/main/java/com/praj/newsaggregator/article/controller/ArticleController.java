package com.praj.newsaggregator.article.controller;

import com.praj.newsaggregator.article.dto.ArticleResponse;
import com.praj.newsaggregator.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

//    @GetMapping
//    public List<ArticleResponse> getAll() {
//        return articleService.getAll();
//    }

    @GetMapping
    public Page<ArticleResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return articleService.getAll(page, size);
    }

    @GetMapping("/category/{category}")
    public Page<ArticleResponse> getByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return articleService.getByCategory(category, page, size);
    }

    @GetMapping("/source/{source}")
    public Page<ArticleResponse> getBySource(
            @PathVariable String source,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return articleService.getBySource(source, page, size);
    }
}