package com.praj.newsaggregator.source.entity;

import com.praj.newsaggregator.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sources")
public class Source extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String rssUrl;

    @Column(nullable = false)
    private String category;

    private boolean enabled = true;

}