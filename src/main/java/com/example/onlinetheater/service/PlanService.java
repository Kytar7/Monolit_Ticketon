package com.example.onlinetheater.service;

import com.example.onlinetheater.model.Plan;
import java.util.List;

public interface PlanService {
    List<Plan> getAllPlans();
    Plan getPlanById(Long id);
    Plan savePlan(Plan plan);
    void deletePlan(Long id);
} 