package com.example.onlinetheater.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "series_watch_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesWatchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchId;

    private Integer lastPosition;
    private LocalDateTime watchDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

    @ManyToOne
    @JoinColumn(name = "episode_id")
    private Episode episode;
}
