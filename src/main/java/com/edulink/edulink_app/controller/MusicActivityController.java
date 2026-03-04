package com.edulink.edulink_app.controller;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edulink.edulink_app.dto.ChallengeResponseDTO;
import com.edulink.edulink_app.model.MusicChallenge;
import com.edulink.edulink_app.model.User;
import com.edulink.edulink_app.repository.MusicChallengeRepository;
import com.edulink.edulink_app.repository.UserRepository;
import com.edulink.edulink_app.service.ChallengeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MusicActivityController {

    private final MusicChallengeRepository musicRepository; 
    private final UserRepository userRepository;
    private final ChallengeService challengeService; 

    @GetMapping("/activity/music")
    public String showMusicActivity(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "0") int index,
            Model model) {
        
        // Buscar Usuario por email
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        // Obtener solo retos de música para el nivel del usuario
        List<MusicChallenge> challenges = musicRepository.findByLevel(user.getLevel());

        if (challenges.isEmpty() || index >= challenges.size()) {
            return "redirect:/dashboard?finish=true";
        }

        // 3. Obtenemos el reto actual y lo convertimos a DTO
        MusicChallenge currentChallenge = challenges.get(index);
        ChallengeResponseDTO challengeDTO = challengeService.mapToDTO(currentChallenge);
        
        // --- Lógica de Aleatoriedad ---
        Collections.shuffle(challengeDTO.options()); 

        model.addAttribute("challenge", challengeDTO);
        model.addAttribute("currentIndex", index);
        model.addAttribute("nextIndex", index + 1);
        model.addAttribute("totalChallenges", challenges.size());
        
        return "activities/music";
    }
}