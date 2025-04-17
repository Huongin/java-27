package com.example.movie_app.repository;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository <Movie, Integer> {
    Movie findByName(String name);

    List<Movie> findByNameContaining(String name);

    // Page bat dau = 0 tuong ung voi trang 1
    Page<Movie> findByNameContaining(String name, Pageable pageable);

    List<Movie> findByNameContainingIgnoreCase(String name);

    List<Movie> findByRatingBetween(Double min, Double max);

    List<Movie> findByRatingGreaterThan(Double rating);


    List<Movie> findByRatingLessThan(Double rating, Sort sort);
    //C1: Method query:Độc lập với cơ sở dữ liệu
    //select * from movies where rating <? order by rating decs
    List<Movie> findByRatingLessThanOrderByRatingDesc(Double rating);

    //C2: native query: Phụ thuộc vào cơ sở dữ liệu
    @Query(value = "select * from movies where rating < ?1 order by rating desc", nativeQuery = true)
    List<Movie> findByRatingLessThanOrderByRatingDesc_NQ1(Double rating);

    @Query(value = "select * from movies where rating < :rating order by rating desc", nativeQuery = true)
    List<Movie> findByRatingLessThanOrderByRatingDesc_NQ2(@Param("rating") Double rating);

    //C3: JPQL:Độc lập với cơ sở dữ liệu
    @Query(value = "select m from Movie m where m.rating < ?1 order by m.rating desc")
    List<Movie> findByRatingLessThanOrderByRatingDesc_JPQL(Double rating);

    Boolean existsByName(String name);

    Long countByRating(Double rating);

    void deleteByName(String name);

    List<Movie> findByStatusTrue();

    Page<Movie> findByTypeAndStatus(MovieType type, Boolean status, Pageable pageable);

    @Query(value = "select * from movies where status = ?1 order by rating desc limit ?2", nativeQuery = true)
    List<Movie> findHotMovie(boolean status, int limit);

    @Query (value = "select m FROM Movie m WHERE m.id = :id AND m.slug = :slug AND m.status = :status")
    Movie findByIdAndSlugAndStatus(Integer id, String slug, boolean status);

    @Query(value = "select * FROM movies WHERE type = ?1 AND status = ?2 ORDER BY rating desc limit ?3", nativeQuery = true)
    List<Movie> findRecommendedMovies(String type, boolean status, int limit);

    Optional<Movie> findByIdAndStatusTrue(Integer movieId);
}
