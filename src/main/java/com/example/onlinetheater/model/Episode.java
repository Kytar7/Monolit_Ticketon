package com.example.onlinetheater.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "episodes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long episodeId;

    private int episodeNumber;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private Integer duration;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;
}
