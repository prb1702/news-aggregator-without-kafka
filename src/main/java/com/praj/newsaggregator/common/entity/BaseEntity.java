package com.praj.newsaggregator.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//Why make the class abstract?
//You never want -> new BaseEntity()
//because it doesn't represent any real business object.
//Instead classes should extend it.
//forms IS-A relationship
//used for inheritance

//An embeddable cannot define its own entity identity (@Id). The owning entity is responsible for its primary key. So this won't work.

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}