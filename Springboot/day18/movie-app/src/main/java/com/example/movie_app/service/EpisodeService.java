package com.example.movie_app.service;


import com.example.movie_app.entity.Episode;
import com.example.movie_app.entity.Movie;
import com.example.movie_app.exception.NotFoundException;
import com.example.movie_app.model.request.CreateEpisodeRequest;
import com.example.movie_app.model.request.UpdateEpisodeRequest;
import com.example.movie_app.repository.EpisodeRepository;
import com.example.movie_app.repository.MovieRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final MovieRepository movieRepository;


    public List<Episode> findEpisodesByMovieId(Integer id) {
        return episodeRepository.findByMovie_IdAndStatusOrderByDisplayOrderAsc(id, true);
    }

    public Episode findEpisodeByDisplayOrder(Integer id, String tap) {
        Integer displayOrder = tap.equals("full") ? 1 : Integer.parseInt(tap);
        return episodeRepository
                .findByMovie_IdAndDisplayOrderAndStatus(id, displayOrder, true);
    }

    public Page<Episode> getEpisodesByMovieId(Integer movieId, Integer page, Integer pageSize) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phim với id = " + movieId));
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("episodeNumber").ascending());
        return episodeRepository.findByMovie(movie, pageable);
    }

    public void createEpisode( CreateEpisodeRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phim với id = " + request.getMovieId()));

        Episode episode = new Episode();
        episode.setName(request.getName());
        episode.setDisplayOrder(request.getDisplayOrder());
        episode.setStatus(request.getStatus());
        episode.setMovie(movie);

        episodeRepository.save(episode);
    }

    public void updateEpisode(Integer id, UpdateEpisodeRequest request) {
        // Kiểm tra xem tập phim có tồn tại không
        Episode existingEpisode = episodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy tập phim với id: " + id));

        // Cập nhật các thông tin tập phim từ request
        existingEpisode.setName(request.getName());
        existingEpisode.setDisplayOrder(request.getDisplayOrder());
        existingEpisode.setStatus(request.getStatus());

        // Lưu lại thông tin tập phim đã được cập nhật
        episodeRepository.save(existingEpisode);
    }

    public void deleteEpisode(Integer id) {
        // Kiểm tra xem tập phim có tồn tại không
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy tập phim với id: " + id));

        // Xóa tập phim
        episodeRepository.delete(episode);
    }
}

