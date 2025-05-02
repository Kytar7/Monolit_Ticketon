package com.example.onlinetheater.service.impl;

import com.example.onlinetheater.model.Movie;
import com.example.onlinetheater.repository.MovieRepository;
import com.example.onlinetheater.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    @Override
    public List<Movie> getPopularMovies() {
        return movieRepository.findTop10ByOrderByRatingDesc();
    }

    @Override
    public List<Movie> getRecentMovies() {
        return movieRepository.findTop10ByOrderByReleaseDateDesc();
    }

    @Override
    public List<Movie> searchMovies(String query) {
        return movieRepository.findByTitleContainingIgnoreCase(query);
    }

    @Override
    public List<Movie> findByGenre_GenreId(Long genreId) {
        return movieRepository.findByGenre_GenreId(genreId);
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
} 