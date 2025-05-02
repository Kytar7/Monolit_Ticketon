package com.example.onlinetheater.controller;

import com.example.onlinetheater.model.User;
import com.example.onlinetheater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String username,
                       @RequestParam String password,
                       RedirectAttributes redirectAttributes) {
        try {
            userService.login(username, password);
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Неверное имя пользователя или пароль");
            return "redirect:/login";
        }
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                          BindingResult bindingResult,
                          @RequestParam String confirmPassword,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (!user.getPassword().equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Пароли не совпадают");
            return "redirect:/register";
        }

        try {
            userService.register(user);
            return "redirect:/login?registered=true";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ошибка при регистрации: " + e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email,
                                      RedirectAttributes redirectAttributes) {
        try {
            userService.sendPasswordResetEmail(email);
            redirectAttributes.addFlashAttribute("message", "Инструкции по сбросу пароля отправлены на ваш email");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ошибка при отправке email: " + e.getMessage());
            return "redirect:/forgot-password";
        }
    }
} 