package com.example.movie_app.controller.web;

import com.example.movie_app.entity.Episode;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.dto.UserDTO;
import com.example.movie_app.model.enums.MovieType;
import com.example.movie_app.service.EpisodeService;
import com.example.movie_app.service.FavoriteService;
import com.example.movie_app.service.MovieService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class webController {

    private final MovieService movieService;
    private final EpisodeService episodeService;
    private final FavoriteService favoriteService;
    private final HttpSession session;



    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Movie> hotMovies = movieService.findHotMovie(true, 4);
        List<Movie> phimLeList = movieService.findByType(MovieType.PHIM_LE, true, 1, 6).getContent();
        List<Movie> phimBoList = movieService.findByType(MovieType.PHIM_BO, true, 1, 6).getContent();
        List<Movie> phimChieuRapList = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, 1, 6).getContent();
        model.addAttribute("hotMovies", hotMovies);
        model.addAttribute("phimLeList", phimLeList);
        model.addAttribute("phimBoList", phimBoList);
        model.addAttribute("phimChieuRapList", phimChieuRapList);
        return "web/index";
    }

    @GetMapping("/phim-bo")
    public String getPhimBoPage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "web/phim-bo";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "18") Integer pageSize,
                                      Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "web/phim-chieu-rap";
    }

    @GetMapping("/phim-le")
    public String getPhimLePage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "web/phim-le";
    }


    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(@PathVariable Integer id,
                                      @PathVariable String slug,
                                      Model model) {
        // Lay thong tin movie
        Movie movie = movieService.findMovieDetail(id, slug);
        model.addAttribute("movie", movie);

        String movieTypeDisplay = movie.getType().getValue();
        model.addAttribute("movieTypeDisplay", movieTypeDisplay);

        List<Movie> recommendedMovies = movieService.findRecommendedMovies(movie.getType(), true, 6);
        model.addAttribute("recommendedMovies", recommendedMovies);
        System.out.println("Số phim đề xuất: " + recommendedMovies.size());


        //Lấy danh sách tập phim theo movie id, status
        List<Episode> episodes = episodeService.findEpisodesByMovieId(id);
        model.addAttribute("episodes", episodes);

        //Kiểm tra phim có trong danh sách yêu thích của user ko
        Boolean isFavorite = true;
        model.addAttribute("isFavorite", isFavorite);

        return "web/chi-tiet-phim";
    }

    @GetMapping("/xem-phim/{id}/{slug}")
    public String getWatchMovieDetailsPage(@PathVariable Integer id,
                                           @PathVariable String slug,
                                           Model model,
                                           @RequestParam String tap) {
        //Lay thong tin movie
        Movie movie = movieService.findMovieDetail(id, slug);
        model.addAttribute("movie", movie);

        List<Movie> recommendedMovies = movieService.findRecommendedMovies(movie.getType(), true, 6);
        String movieTypeDisplay = movie.getType().getValue();
        model.addAttribute("movieTypeDisplay", movieTypeDisplay);

        //Lay danh sach tap phim cua phim bo(lay theo movie ID, status = true, sorting theo displayOrder asc)
        List<Episode> episodes = episodeService.findEpisodesByMovieId(id);
        model.addAttribute("episodes", episodes);

        //Lay thong tin tap phim
        Episode episode = episodeService.findEpisodeByDisplayOrder(id, tap);
        model.addAttribute("episode", episode);
        return "web/xem-phim";
    }

    @GetMapping("/phim-yeu-thich")
    public String getFavoritePage(Model model) {
        return "web/phim-yeu-thich";
    }

    @GetMapping("/dang-nhap")
    public String showLoginPage() {
        UserDTO currentUser = (UserDTO) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "web/login";
        }
        return"redirect:/";
    }

}
