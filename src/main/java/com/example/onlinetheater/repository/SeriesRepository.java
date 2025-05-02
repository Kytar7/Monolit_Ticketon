package com.example.onlinetheater.repository;

import com.example.onlinetheater.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findTop10ByOrderByRatingDesc();
    List<Series> findTop10ByOrderByReleaseDateDesc();
    List<Series> findByTitleContainingIgnoreCase(String title);
    List<Series> findByGenre_GenreId(Long genreId);
}
