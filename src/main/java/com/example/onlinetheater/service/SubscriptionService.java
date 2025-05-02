package com.example.onlinetheater.service;

import com.example.onlinetheater.model.Plan;
import com.example.onlinetheater.model.Subscription;
import java.util.List;

public interface SubscriptionService {
    List<Plan> getAllPlans();
    Subscription createSubscription(String username, Long planId);
    void cancelSubscription(String username);
    List<Subscription> getUserSubscriptionHistory(String username);
    boolean hasActiveSubscription(String username);
} 