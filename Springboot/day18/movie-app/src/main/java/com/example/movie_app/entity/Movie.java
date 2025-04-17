package com.example.movie_app.entity;


import com.example.movie_app.model.enums.MovieType;
import jakarta.persistence.*;
import lombok.*;



import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany
    @JoinTable(
            name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;

    @ManyToMany
    @JoinTable(
            name = "movies_directors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private List<Director> directors;
}
