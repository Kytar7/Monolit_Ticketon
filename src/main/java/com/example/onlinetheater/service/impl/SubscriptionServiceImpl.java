package com.example.onlinetheater.service.impl;

import com.example.onlinetheater.model.Plan;
import com.example.onlinetheater.model.Subscription;
import com.example.onlinetheater.model.User;
import com.example.onlinetheater.repository.PlanRepository;
import com.example.onlinetheater.repository.SubscriptionRepository;
import com.example.onlinetheater.repository.UserRepository;
import com.example.onlinetheater.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    @Override
    public Subscription createSubscription(String username, Long planId) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Plan plan = planRepository.findById(planId)
            .orElseThrow(() -> new RuntimeException("Plan not found"));

        // Cancel any active subscription
        cancelSubscription(username);

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(LocalDateTime.now().plusDays(plan.getDuration()));
        subscription.setActive(true);

        return subscriptionRepository.save(subscription);
    }

    @Override
    public void cancelSubscription(String username) {
        List<Subscription> activeSubscriptions = subscriptionRepository
            .findByUserUsernameAndActiveTrue(username);
        
        for (Subscription subscription : activeSubscriptions) {
            subscription.setActive(false);
            subscription.setEndDate(LocalDateTime.now());
            subscriptionRepository.save(subscription);
        }
    }

    @Override
    public List<Subscription> getUserSubscriptionHistory(String username) {
        return subscriptionRepository.findByUserUsernameOrderByStartDateDesc(username);
    }

    @Override
    public boolean hasActiveSubscription(String username) {
        return subscriptionRepository.existsByUserUsernameAndActiveTrue(username);
    }
} 