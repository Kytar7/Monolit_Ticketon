package com.example.onlinetheater.controller;

import com.example.onlinetheater.model.*;
import com.example.onlinetheater.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            User currentUser = userService.getCurrentUser();
            model.addAttribute("user", currentUser);
            model.addAttribute("isSubscribed", subscriptionService.hasActiveSubscription(currentUser.getUsername()));
        }

        List<Movie> popularMovies = movieService.getPopularMovies();
        List<Series> popularSeries = seriesService.getPopularSeries();
        
        model.addAttribute("popularMovies", popularMovies);
        model.addAttribute("popularSeries", popularSeries);
        return "index";
    }

    @GetMapping("/movies")
    public String movies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/movies/{id}")
    public String movieDetails(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "movie-details";
    }

    @GetMapping("/series")
    public String series(Model model) {
        List<Series> series = seriesService.getAllSeries();
        model.addAttribute("series", series);
        return "series";
    }

    @GetMapping("/series/{id}")
    public String seriesDetails(@PathVariable Long id, Model model) {
        Series series = seriesService.getSeriesById(id);
        model.addAttribute("series", series);
        return "series-details";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

        boolean hasSubscription = subscriptionService.hasActiveSubscription(currentUser.getUsername());
        model.addAttribute("user", currentUser);
        model.addAttribute("hasSubscription", hasSubscription);
        
        if (hasSubscription) {
            List<Movie> recentMovies = movieService.getRecentMovies();
            List<Series> recentSeries = seriesService.getRecentSeries();
            model.addAttribute("recentMovies", recentMovies);
            model.addAttribute("recentSeries", recentSeries);
        }

        return "dashboard";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", currentUser);
        model.addAttribute("subscriptions", 
            subscriptionService.getUserSubscriptionHistory(currentUser.getUsername()));
        return "profile";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String query, 
                        @RequestParam(required = false) String type,
                        @RequestParam(required = false) Long genreId,
                        Model model) {
        if (query != null && !query.trim().isEmpty()) {
            if (type == null || type.equals("movies")) {
                List<Movie> movies = movieService.searchMovies(query);
                model.addAttribute("movies", movies);
            }
            if (type == null || type.equals("series")) {
                List<Series> series = seriesService.searchSeries(query);
                model.addAttribute("series", series);
            }
        }

        if (genreId != null) {
            if (type == null || type.equals("movies")) {
                List<Movie> moviesByGenre = movieService.findByGenre_GenreId(genreId);
                model.addAttribute("moviesByGenre", moviesByGenre);
            }
            if (type == null || type.equals("series")) {
                List<Series> seriesByGenre = seriesService.findByGenre_GenreId(genreId);
                model.addAttribute("seriesByGenre", seriesByGenre);
            }
        }

        model.addAttribute("query", query);
        model.addAttribute("type", type);
        model.addAttribute("genreId", genreId);
        return "search";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/terms")
    public String terms() {
        return "terms";
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
} 