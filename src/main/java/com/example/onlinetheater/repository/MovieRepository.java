package com.example.onlinetheater.repository;

import com.example.onlinetheater.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findTop10ByOrderByRatingDesc();
    List<Movie> findTop10ByOrderByReleaseDateDesc();
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByGenre_GenreId(Long genreId);
}
