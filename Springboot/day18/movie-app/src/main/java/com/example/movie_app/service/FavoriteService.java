package com.example.movie_app.service;

import com.example.movie_app.entity.Favorite;
import com.example.movie_app.entity.Movie;
import com.example.movie_app.entity.User;
import com.example.movie_app.exception.BadRequestException;
import com.example.movie_app.exception.NotFoundException;
import com.example.movie_app.repository.FavoryRepository;
import com.example.movie_app.repository.MovieRepository;
import com.example.movie_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoryRepository favoryRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public Page<Favorite> getFavorites(Integer page, Integer pageSize) {
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user có id = " + userId));
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.by("createdAt").descending());

        return favoryRepository.findByUser_Id(user.getId(), pageable);
    }

    //Thêm vào danh sách yêu thích
    public Favorite addFavorite(Integer movieId){
       Integer userId = 1;
       User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user có id = " + userId));
       Movie movie = movieRepository.findByIdAndStatusTrue(movieId)
               .orElseThrow(() -> new NotFoundException("Không tìm thấy phim có id = " + movieId));
        Optional<Favorite> favoriteOptional = favoryRepository.findByUser_IdAndMovie_Id(userId,movieId);
        if(favoriteOptional.isPresent()){
            throw new BadRequestException("Phim đã có trong danh sách yêu thích");
        }
        //Tạo danh sách yêu thích
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setMovie(movie);
        favorite.setCreatedAt(LocalDateTime.now());
        return favoryRepository.save(favorite);
    }

    //Xóa khỏi danh sách yêu thích
    public void removeFavorite(Integer movieId){
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user có id = " + userId));
        Favorite favorite = favoryRepository.findByUser_IdAndMovie_Id(userId,movieId)
                .orElseThrow(()->new NotFoundException("Phim không có trong danh sách yêu thích"));
        favoryRepository.delete(favorite);
    }

    //Xóa tất cả danh sách yêu thích
    public void removeAllFavorites() {
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user có id = " + userId));
        Page<Favorite> favorites = favoryRepository.findByUser_Id(userId, Pageable.unpaged());
        favoryRepository.deleteAll(favorites.getContent());
    }
}
