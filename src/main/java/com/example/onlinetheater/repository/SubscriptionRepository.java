package com.example.onlinetheater.repository;

import com.example.onlinetheater.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserUsernameAndActiveTrue(String username);
    List<Subscription> findByUserUsernameOrderByStartDateDesc(String username);
    boolean existsByUserUsernameAndActiveTrue(String username);
} 