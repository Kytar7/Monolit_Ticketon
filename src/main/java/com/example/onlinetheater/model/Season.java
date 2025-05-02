package com.example.onlinetheater.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "seasons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;

    private int seasonNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private Set<Episode> episodes = new HashSet<>();
}
