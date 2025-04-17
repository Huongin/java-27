package com.example.movie_app.model.request;

import com.example.movie_app.model.enums.MovieType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMovieRequest {
    private String name;
    private String trailer;
    private String description;
    private Integer releaseYear;
    private MovieType type;
    private Boolean status;
    private Integer countryId;
    private List<Integer> genreIds = new ArrayList<>();
    private List<Integer> actorIds = new ArrayList<>();
    private List<Integer> directorIds = new ArrayList<>();
}
