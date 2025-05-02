package com.example.onlinetheater.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

//import jasvax.persistence.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Subscription> subscriptions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<MovieReview> movieReviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<SeriesReview> seriesReviews;
}
