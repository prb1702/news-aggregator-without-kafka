package com.praj.newsaggregator.article.mapper;

import com.praj.newsaggregator.article.dto.ArticleResponse;
import com.praj.newsaggregator.article.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(target = "sourceName", source = "source.name")
    ArticleResponse toResponse(Article article);

}