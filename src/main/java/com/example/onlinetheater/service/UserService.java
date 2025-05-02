package com.example.onlinetheater.service;

import com.example.onlinetheater.model.User;

public interface UserService {
    User login(String username, String password);
    User register(User user);
    void logout();
    void sendPasswordResetEmail(String email);
    User getCurrentUser();
    boolean isUserSubscribed();
} 