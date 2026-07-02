package com.praj.newsaggregator.source.repository;

import com.praj.newsaggregator.source.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, Long> {

    List<Source> findByEnabledTrue();

}