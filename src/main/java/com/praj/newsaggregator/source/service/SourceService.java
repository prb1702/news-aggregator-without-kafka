package com.praj.newsaggregator.source.service;

import com.praj.newsaggregator.common.exception.DuplicateResourceException;
import com.praj.newsaggregator.common.exception.ResourceNotFoundException;
import com.praj.newsaggregator.source.dto.SourceRequest;
import com.praj.newsaggregator.source.dto.SourceResponse;
import com.praj.newsaggregator.source.entity.Source;
import com.praj.newsaggregator.source.mapper.SourceMapper;
import com.praj.newsaggregator.source.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SourceService {

    private final SourceRepository sourceRepository;

    // <-- THIS is where we inject it
    private final SourceMapper sourceMapper;

    public SourceResponse create(SourceRequest request) {

        if (sourceRepository.existsByRssUrl(request.getRssUrl())) {
            throw new DuplicateResourceException("RSS Source already exists");
        }

        Source source = sourceMapper.toEntity(request);

        return sourceMapper.toResponse(
                sourceRepository.save(source)
        );
    }

    public List<SourceResponse> getAll() {

        return sourceRepository.findAll()
                .stream()
                .map(sourceMapper::toResponse)
                .toList();

    }

    public SourceResponse getById(Long id) {

        Source source = sourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Source not found with id: " + id));

        return sourceMapper.toResponse(source);

    }

    public SourceResponse update(Long id, SourceRequest request) {

        Source source = sourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Source not found with id: " + id));

        source.setName(request.getName());
        source.setRssUrl(request.getRssUrl());
        source.setCategory(request.getCategory());

        return sourceMapper.toResponse(
                sourceRepository.save(source)
        );

    }

    public void delete(Long id) {

        if (!sourceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Source not found with id: " + id);
        }

        sourceRepository.deleteById(id);

    }

}