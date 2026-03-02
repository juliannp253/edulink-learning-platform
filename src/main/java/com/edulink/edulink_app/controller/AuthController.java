package com.edulink.edulink_app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.edulink.edulink_app.dto.UserRegistrationRequest;
import com.edulink.edulink_app.exception.UserAlreadyExistsException;
import com.edulink.edulink_app.model.UserLevel;
import com.edulink.edulink_app.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @ModelAttribute("levels")
    public UserLevel[] getLevels() {
        return UserLevel.values();
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationRequest("", "", "", ""));
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationRequest registrationRequest,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            return "register"; 
        }
        try {
            userService.saveUser(registrationRequest);
            return "redirect:/login?success";
        }   catch(UserAlreadyExistsException ex) {
            model.addAttribute("error", ex.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
