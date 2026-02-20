package com.edulink.edulink_app.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edulink.edulink_app.model.User;
import com.edulink.edulink_app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final UserRepository userRepository;

    @PostMapping("/add-points")
    public ResponseEntity<?> addPoints(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        
        // Sumamos, por ejemplo, 10 puntos por acierto
        user.setTotalPoints(user.getTotalPoints() + 10);
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("newTotal", user.getTotalPoints()));
    }
}