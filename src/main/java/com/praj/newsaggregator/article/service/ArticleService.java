package com.praj.newsaggregator.article.service;

import com.praj.newsaggregator.article.dto.ArticleResponse;
import com.praj.newsaggregator.article.mapper.ArticleMapper;
import com.praj.newsaggregator.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

//    public List<ArticleResponse> getAll() {
//        return articleRepository.findAll()
//                .stream()
//                .map(articleMapper::toResponse)
//                .toList();
//    }
//    public List<ArticleResponse> getAll() {
//        return articleRepository.findAllWithSource()
//                .stream()
//                .map(articleMapper::toResponse)
//                .toList();
//    }
    public Page<ArticleResponse> getAll(int page, int size) {

        return articleRepository.findAll(PageRequest.of(page, size))
                .map(articleMapper::toResponse);
    }

    public Page<ArticleResponse> getByCategory(String category, int page, int size) {

        return articleRepository.findByCategoryIgnoreCase(category, PageRequest.of(page, size))
                .map(articleMapper::toResponse);
    }

    public Page<ArticleResponse> getBySource(String source, int page, int size) {

        return articleRepository.findBySource_NameIgnoreCase(source, PageRequest.of(page, size))
                .map(articleMapper::toResponse);
    }
}