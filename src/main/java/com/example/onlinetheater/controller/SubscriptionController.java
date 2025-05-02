package com.example.onlinetheater.controller;

import com.example.onlinetheater.model.Plan;
import com.example.onlinetheater.model.Subscription;
import com.example.onlinetheater.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public String showPlans(Model model) {
        model.addAttribute("plans", subscriptionService.getAllPlans());
        return "subscription";
    }

    @PostMapping("/process")
    public String processSubscription(@RequestParam Long planId,
                                    @AuthenticationPrincipal UserDetails userDetails,
                                    Model model) {
        try {
            Subscription subscription = subscriptionService.createSubscription(
                userDetails.getUsername(),
                planId
            );
            return "redirect:/subscription/success";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при оформлении подписки: " + e.getMessage());
            return "subscription";
        }
    }

    @GetMapping("/success")
    public String subscriptionSuccess() {
        return "subscription-success";
    }

    @GetMapping("/cancel")
    public String cancelSubscription(@AuthenticationPrincipal UserDetails userDetails) {
        subscriptionService.cancelSubscription(userDetails.getUsername());
        return "redirect:/subscription/cancelled";
    }

    @GetMapping("/cancelled")
    public String subscriptionCancelled() {
        return "subscription-cancelled";
    }

    @GetMapping("/history")
    public String subscriptionHistory(@AuthenticationPrincipal UserDetails userDetails,
                                    Model model) {
        model.addAttribute("subscriptions", 
            subscriptionService.getUserSubscriptionHistory(userDetails.getUsername()));
        return "subscription-history";
    }
} 