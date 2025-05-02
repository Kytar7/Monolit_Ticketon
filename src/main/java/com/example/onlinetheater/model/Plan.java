package com.example.onlinetheater.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    private String name;
    private Double price;
    private Integer duration;
    private String description;

    @OneToMany(mappedBy = "plan")
    private Set<Subscription> subscriptions = new HashSet<>();
}
