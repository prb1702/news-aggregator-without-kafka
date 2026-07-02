package com.praj.newsaggregator.source.controller;

import com.praj.newsaggregator.source.dto.SourceRequest;
import com.praj.newsaggregator.source.dto.SourceResponse;
import com.praj.newsaggregator.source.service.SourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sources")
@RequiredArgsConstructor
public class SourceController {

    private final SourceService sourceService;

    @PostMapping
    public SourceResponse create(@RequestBody @Valid SourceRequest request) {

        return sourceService.create(request);

    }

    @GetMapping
    public List<SourceResponse> getAll() {

        return sourceService.getAll();

    }

    @GetMapping("/{id}")
    public SourceResponse getById(@PathVariable Long id) {

        return sourceService.getById(id);

    }

    @PutMapping("/{id}")
    public SourceResponse update(@PathVariable Long id,
                                 @RequestBody @Valid SourceRequest request) {

        return sourceService.update(id, request);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        sourceService.delete(id);

    }

}