package com.praj.newsaggregator.source.mapper;

import com.praj.newsaggregator.source.dto.SourceRequest;
import com.praj.newsaggregator.source.dto.SourceResponse;
import com.praj.newsaggregator.source.entity.Source;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper {

    Source toEntity(SourceRequest request);

    SourceResponse toResponse(Source source);

}