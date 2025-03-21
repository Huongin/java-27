package com.example.movie_app.controller.web;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.enums.MovieType;
import com.example.movie_app.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class webController {

    private final MovieService movieService;

    public webController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String getHomePage(Model model){
        List<Movie> hotMovies = movieService.findHotMovie(true, 4);
        List<Movie> phimLeList = movieService.findByType(MovieType.PHIM_LE,true, 1,6).getContent();
        List<Movie> phimBoList = movieService.findByType(MovieType.PHIM_BO,true, 1,6).getContent();
        List<Movie> phimChieuRapList = movieService.findByType(MovieType.PHIM_CHIEU_RAP,true, 1,6).getContent();
        model.addAttribute("hotMovies", hotMovies);
        model.addAttribute("phimLeList", phimLeList);
        model.addAttribute("phimBoList", phimBoList);
        model.addAttribute("phimChieuRapList", phimChieuRapList);
        return "index";
    }

    @GetMapping("/phim-bo")
    public String getPhimBoPage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "phim-bo";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "18") Integer pageSize,
                                      Model model){
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "phim-chieu-rap";
    }

    @GetMapping("/phim-le")
    public String getPhimLePage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model){
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_LE, true, page,pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "phim-le";
    }


    @GetMapping("/phim/{id}/{slug}")
    public  String getMovieDetailsPage(@PathVariable Integer id,
                                       @PathVariable String slug,
                                       Model model){
        Movie movie = movieService.findMovieDetail(id, slug);
        List<Movie> recommendedMovies = movieService.findRecommendedMovies(movie.getType(), true, 6);
        String movieTypeDisplay = movie.getType().getDisplayName();

        model.addAttribute("movie", movie);
        model.addAttribute("recommendedMovies", recommendedMovies);
        model.addAttribute("movieTypeDisplay", movieTypeDisplay);
        return "chi-tiet-phim";
    }
}
