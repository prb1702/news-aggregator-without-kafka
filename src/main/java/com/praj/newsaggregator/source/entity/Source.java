package com.praj.newsaggregator.source.entity;

import com.praj.newsaggregator.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sources")
public class Source extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String rssUrl;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private boolean enabled = true;

}