package com.example.movie_app.api;


import com.example.movie_app.entity.Favorite;
import com.example.movie_app.model.request.AddFavoriteRequest;
import com.example.movie_app.model.request.RemoveFavoriteRequest;
import com.example.movie_app.service.FavoriteService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteApi {
    private final FavoriteService favoriteService;

    // Lấy danh sách phim yêu thích
    @GetMapping
    public ResponseEntity<?> getFavorites(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "12") Integer pageSize
    ){
        Page<Favorite> favoritePage = favoriteService.getFavorites(page

                , pageSize);
        return ResponseEntity.ok(favoritePage);
    }

    //Thêm vào danh sách yêu thích
    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(@RequestBody AddFavoriteRequest request){
        Favorite favorite = favoriteService.addFavorite(request.getMovieId());
        return ResponseEntity.ok(favorite);
    }

    //Xóa phim ra khỏi danh sách yêu thích
    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFavorite(@RequestBody RemoveFavoriteRequest request){
        favoriteService.removeFavorite(request.getMovieId());
        return ResponseEntity.ok().build();
    }

    //Xóa tất cả phim yêu thích
    @DeleteMapping("/removeAll")
    public ResponseEntity<?> removeAllFavorites(){
        favoriteService.removeAllFavorites();
        return ResponseEntity.ok().build();
    }
}
