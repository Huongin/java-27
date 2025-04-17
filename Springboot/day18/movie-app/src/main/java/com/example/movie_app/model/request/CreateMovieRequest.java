package com.example.movie_app.model.request;

import com.example.movie_app.model.enums.MovieType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class CreateMovieRequest {
    @NotEmpty
    private String name;

    @NotEmpty
    private String trailer;

    @NotEmpty
    private String description;

    @NotNull
    private Integer releaseYear;

    @NotNull
    private MovieType type;

    @NotNull
    private Boolean status;

    @NotNull
    private Integer countryId;

    private List<Integer> genreIds = new ArrayList<>();
    private List<Integer> actorIds = new ArrayList<>();
    private List<Integer> directorIds = new ArrayList<>();
}
