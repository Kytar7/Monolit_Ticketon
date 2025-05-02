package com.example.onlinetheater.service;

import com.example.onlinetheater.model.Movie;
import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    List<Movie> getPopularMovies();
    List<Movie> getRecentMovies();
    List<Movie> searchMovies(String query);
    List<Movie> findByGenre_GenreId(Long genreId);
    Movie getMovieById(Long id);
    Movie saveMovie(Movie movie);
    void deleteMovie(Long id);
} 