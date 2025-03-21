package com.example.movie_app.entity;


import com.example.movie_app.model.enums.MovieType;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id tự động
    private Integer id;

    @Column(nullable = false) //Ko được để trống, tên duy nhất
    private String name;
    private String slug;

    @Column(columnDefinition = "Text")
    private String description;

    private String thumbnail;
    private Integer releaseYear;
    private Boolean status;
    private String trailer;

    @Column(columnDefinition = "double default 5.0")
    private Double rating;

    //type
    @Enumerated(EnumType.STRING)
    private MovieType type;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
}
