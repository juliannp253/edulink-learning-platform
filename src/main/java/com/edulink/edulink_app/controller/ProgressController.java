package com.edulink.edulink_app.controller;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edulink.edulink_app.dto.AnswerRequestDTO;
import com.edulink.edulink_app.model.User;
import com.edulink.edulink_app.repository.UserRepository;
import com.edulink.edulink_app.service.ChallengeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final UserRepository userRepository;
    private final ChallengeService challengeService; 

    @PostMapping("/submit")
    public ResponseEntity<?> submitChallenge(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody AnswerRequestDTO answerRequest) { 
        
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        

        boolean isCorrect = challengeService.verifyAndAwardPoints(
                answerRequest.challengeId(), 
                answerRequest.selectedOption(), 
                user);

        return ResponseEntity.ok(Map.of(
            "correct", isCorrect,
            "newTotal", user.getTotalPoints(),
            "message", isCorrect ? "¡Felicidades! +10 puntos" : "Casi, ¡inténtalo de nuevo!"
        ));
    }
}