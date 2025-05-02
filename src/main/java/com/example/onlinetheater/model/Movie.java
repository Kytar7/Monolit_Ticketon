package com.example.onlinetheater.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer releaseYear;
    private Integer duration;
    
    @Column(name = "poster_url")
    private String posterUrl;
    
    @Column(precision = 3, scale = 1)
    private Double rating;
    
    private String director;
    
    @Column(length = 500)
    private String castMembers;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<MovieReview> reviews = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<MovieWatchHistory> watchHistory = new HashSet<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (releaseDate == null && releaseYear != null) {
            releaseDate = LocalDateTime.of(releaseYear, 1, 1, 0, 0);
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
