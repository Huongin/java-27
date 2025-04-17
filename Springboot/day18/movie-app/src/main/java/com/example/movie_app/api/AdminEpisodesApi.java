package com.example.movie_app.api;

import com.example.movie_app.entity.Episode;
import com.example.movie_app.model.request.CreateEpisodeRequest;
import com.example.movie_app.model.request.UpdateEpisodeRequest;
import com.example.movie_app.service.EpisodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/episodes")
@RequiredArgsConstructor
public class AdminEpisodesApi {
    private final EpisodeService episodeService;

    @GetMapping
    public ResponseEntity<?> getEpisodesByMovieId(
            @RequestParam(required = true) Integer movieId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Episode> episodes = episodeService.getEpisodesByMovieId(movieId, page, pageSize);
        return ResponseEntity.ok(episodes);
    }

    @PostMapping
    public ResponseEntity<?> createEpisode(@Valid @RequestBody CreateEpisodeRequest request) {
        episodeService.createEpisode(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tạo tập phim thành công");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEpisode(@PathVariable("id") Integer id,
                                           @Valid @RequestBody UpdateEpisodeRequest request) {
        episodeService.updateEpisode(id, request);
        return ResponseEntity.ok("Cập nhật tập phim thành công");
    }

    @DeleteMapping("/{id}")
    public void deleteEpisode(@PathVariable Integer id) {
        episodeService.deleteEpisode(id); // Gọi service để xóa tập phim
    }
}
