package com.example.movie_app.api;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.request.CreateMovieRequest;
import com.example.movie_app.model.request.UpdateMovieRequest;
import com.example.movie_app.service.MovieService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/movies")
public class AdminMovieApi {

    private MovieService movieService;

    //Lay danh sach phim
    @GetMapping
    public ResponseEntity<?> getMovies(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer pageSize){

        Page<Movie> moviePage = movieService.getMovies(page, pageSize);
        return ResponseEntity.ok(moviePage);
    }
    //Tao moi phim
    @PostMapping
    public ResponseEntity<?> createMovie(@Valid @RequestBody CreateMovieRequest request) {
        Movie movie = movieService.createMovie(request);
        return ResponseEntity.ok(movie);
    }

    //Cap nhat phim
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id,
                                             @RequestBody @Valid UpdateMovieRequest request) {

            Movie movie = movieService.updateMovie(id, request);
            return ResponseEntity.ok(movie);
    }
    //Xoa phim
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
