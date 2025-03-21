package com.example.movie_app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    private String avatar;

    @Column(columnDefinition = "TEXT")
    private String bio;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
