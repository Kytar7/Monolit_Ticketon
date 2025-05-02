package com.example.onlinetheater.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String status;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
}
