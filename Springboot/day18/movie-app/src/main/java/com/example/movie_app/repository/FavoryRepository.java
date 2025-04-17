package com.example.movie_app.repository;

import com.example.movie_app.entity.Favorite;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoryRepository extends JpaRepository<Favorite, Integer> {
    Optional<Favorite> findByUser_IdAndMovie_Id(Integer userId, Integer movieId);

    Page<Favorite> findByUser_Id(Integer id, Pageable pageable);

    boolean existsByUser_IdAndMovie_Id(Integer id, Integer id1);

}
