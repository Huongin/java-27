package com.example.movie_app.service;

import com.example.movie_app.entity.*;
import com.example.movie_app.exception.BadRequestException;
import com.example.movie_app.exception.NotFoundException;
import com.example.movie_app.model.enums.MovieType;
import com.example.movie_app.model.request.CreateMovieRequest;
import com.example.movie_app.model.request.UpdateMovieRequest;
import com.example.movie_app.repository.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CountryRepository countryRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;

    public Page<Movie> findByType(MovieType type, Boolean status, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("publishedAt").descending());
        Page<Movie> moviePage = movieRepository.findByTypeAndStatus(type, status, pageable);
        return moviePage;
    }


    public Movie findMovieDetail(Integer id, String slug) {
      return movieRepository.findByIdAndSlugAndStatus(id, slug, true);
    }

    public List<Movie> findHotMovie(boolean status, int limit) {
        return movieRepository.findHotMovie(status,limit);
    }

    public List<Movie> findRecommendedMovies(MovieType type, boolean status, int limit){
        return movieRepository.findRecommendedMovies(type.name(), status,limit);
    }

    public Page<Movie> getMovies(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("id").descending());
        return movieRepository.findAll(pageable);
    }

    public Movie createMovie(CreateMovieRequest request) {
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy quốc gia với id = " + request.getCountryId()));

        List<Genre> genres = new ArrayList<>();
        if (request.getGenreIds() != null && !request.getGenreIds().isEmpty()) {
            genres = genreRepository.findAllById(request.getGenreIds());
            if (genres.size() != request.getGenreIds().size()) {
                throw new NotFoundException("Một số thể loại không tồn tại");
            }
        }

        List<Actor> actors = new ArrayList<>();
        if (request.getActorIds() != null && !request.getActorIds().isEmpty()) {
            actors = actorRepository.findAllById(request.getActorIds());
            if (actors.size() != request.getActorIds().size()) {
                throw new NotFoundException("Một số diễn viên không tồn tại");
            }
        }

        List<Director> directors = new ArrayList<>();
        if (request.getDirectorIds() != null && !request.getDirectorIds().isEmpty()) {
            directors = directorRepository.findAllById(request.getDirectorIds());
            if (directors.size() != request.getDirectorIds().size()) {
                throw new NotFoundException("Một số đạo diễn không tồn tại");
            }
        }

        // 5. Tạo Movie entity
        Movie movie = Movie.builder()
                .name(request.getName())
                .description(request.getDescription())
                .trailer(request.getTrailer())
                .releaseYear(request.getReleaseYear())
                .type(request.getType()) // Enum MovieType
                .status(request.getStatus())
                .country(country)
                .genres(genres)
                .actors(actors)
                .directors(directors)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return movieRepository.save(movie);
    }

    public Movie updateMovie(Integer id, UpdateMovieRequest request) {

        // Tìm phim cần cập nhật
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phim có id = " + id));

        // Cập nhật các trường (gợi ý dùng Optional cho gọn)
        Optional.ofNullable(request.getName()).ifPresent(movie::setName);
        Optional.ofNullable(request.getTrailer()).ifPresent(movie::setTrailer);
        Optional.ofNullable(request.getDescription()).ifPresent(movie::setDescription);
        Optional.ofNullable(request.getReleaseYear()).ifPresent(movie::setReleaseYear);
        Optional.ofNullable(request.getType()).ifPresent(movie::setType);
        Optional.ofNullable(request.getStatus()).ifPresent(movie::setStatus);

        if (request.getCountryId() != null) {
            Country country = countryRepository.findById(request.getCountryId())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy quốc gia"));
            movie.setCountry(country);
        }

        if (request.getGenreIds() != null && !request.getGenreIds().isEmpty()) {
            List<Genre> genres = genreRepository.findAllById(request.getGenreIds());
            if (genres.size() != request.getGenreIds().size()) {
                throw new NotFoundException("Một số thể loại không tồn tại");
            }
            movie.setGenres(genres);
        }

        if (request.getActorIds() != null && !request.getActorIds().isEmpty()) {
            List<Actor> actors = actorRepository.findAllById(request.getActorIds());
            if (actors.size() != request.getActorIds().size()) {
                throw new NotFoundException("Một số diễn viên không tồn tại");
            }
            movie.setActors(actors);
        }

        if (request.getDirectorIds() != null && !request.getDirectorIds().isEmpty()) {
            List<Director> directors = directorRepository.findAllById(request.getDirectorIds());
            if (directors.size() != request.getDirectorIds().size()) {
                throw new NotFoundException("Một số đạo diễn không tồn tại");
            }
            movie.setDirectors(directors);
        }
        return movieRepository.save(movie);
    }

    public void deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phim có id = " + id));
        movieRepository.delete(movie);
    }
}
