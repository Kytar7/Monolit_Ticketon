package com.example.onlinetheater.service.impl;

import com.example.onlinetheater.model.Series;
import com.example.onlinetheater.repository.SeriesRepository;
import com.example.onlinetheater.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    @Override
    public List<Series> getAllSeries() {
        return seriesRepository.findAll();
    }

    @Override
    public Series getSeriesById(Long id) {
        return seriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found with id: " + id));
    }

    @Override
    public List<Series> getPopularSeries() {
        return seriesRepository.findTop10ByOrderByRatingDesc();
    }

    @Override
    public List<Series> getRecentSeries() {
        return seriesRepository.findTop10ByOrderByReleaseDateDesc();
    }

    @Override
    public List<Series> searchSeries(String query) {
        return seriesRepository.findByTitleContainingIgnoreCase(query);
    }

    @Override
    public List<Series> findByGenre_GenreId(Long genreId) {
        return seriesRepository.findByGenre_GenreId(genreId);
    }

    @Override
    public Series saveSeries(Series series) {
        return seriesRepository.save(series);
    }

    @Override
    public void deleteSeries(Long id) {
        seriesRepository.deleteById(id);
    }
} 