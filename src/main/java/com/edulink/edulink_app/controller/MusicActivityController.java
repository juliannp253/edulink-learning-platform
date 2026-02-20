package com.edulink.edulink_app.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edulink.edulink_app.model.ActivityType;
import com.edulink.edulink_app.model.Challenge;
import com.edulink.edulink_app.model.User;
import com.edulink.edulink_app.repository.ChallengeRepository;
import com.edulink.edulink_app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MusicActivityController {

    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

    @GetMapping("/activity/music")
    public String showMusicActivity(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "0") int index, // Maneja el progreso por índice
            Model model) {
        
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Challenge> challenges = challengeRepository.findByTypeAndLevel(ActivityType.MUSIC, user.getLevel());

        if (challenges.isEmpty() || index >= challenges.size()) {
            return "redirect:/dashboard?finish=true"; // Si terminó todos, vuelve al dashboard
        }

        Challenge currentChallenge = challenges.get(index);
        
        // --- Lógica de Aleatoriedad ---
        List<String> optionsList = Arrays.asList(currentChallenge.getOptions().split(","));
        Collections.shuffle(optionsList); // Mezclamos las opciones para que nunca sea la misma posición

        model.addAttribute("challenge", currentChallenge);
        model.addAttribute("options", optionsList);
        model.addAttribute("currentIndex", index);
        model.addAttribute("nextIndex", index + 1);
        model.addAttribute("totalChallenges", challenges.size());
        
        return "activities/music";
    }
}