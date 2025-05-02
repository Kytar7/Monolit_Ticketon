package com.example.onlinetheater.service;

import com.example.onlinetheater.model.Series;
import java.util.List;

public interface SeriesService {
    List<Series> getAllSeries();
    List<Series> getPopularSeries();
    List<Series> getRecentSeries();
    List<Series> searchSeries(String query);
    List<Series> findByGenre_GenreId(Long genreId);
    Series getSeriesById(Long id);
    Series saveSeries(Series series);
    void deleteSeries(Long id);
} 