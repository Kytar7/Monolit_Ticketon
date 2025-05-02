package com.example.onlinetheater.service.impl;

import com.example.onlinetheater.model.Plan;
import com.example.onlinetheater.repository.PlanRepository;
import com.example.onlinetheater.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    @Override
    public Plan getPlanById(Long id) {
        return planRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Plan not found"));
    }

    @Override
    public Plan savePlan(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }
} 