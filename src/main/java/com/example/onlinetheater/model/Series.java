package com.example.onlinetheater.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "series")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer releaseYear;
    
    @Column(name = "poster_url")
    private String posterUrl;
    
    @Column(precision = 3, scale = 1)
    private Double rating;
    
    private String creator;
    
    @Column(length = 500)
    private String castMembers;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @Column(name = "total_seasons")
    private Integer totalSeasons;

    private Boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private Set<Season> seasons = new HashSet<>();

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private Set<SeriesReview> reviews = new HashSet<>();

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private Set<SeriesWatchHistory> watchHistory = new HashSet<>();

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
