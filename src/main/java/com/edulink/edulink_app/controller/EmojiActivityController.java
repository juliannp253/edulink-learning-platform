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
import com.edulink.edulink_app.model.EmojiChallenge;
import com.edulink.edulink_app.model.User;
import com.edulink.edulink_app.repository.EmojiChallengeRepository;
import com.edulink.edulink_app.repository.UserRepository;
import com.edulink.edulink_app.service.ChallengeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmojiActivityController {

    private final EmojiChallengeRepository emojiRepository; 
    private final UserRepository userRepository;
    private final ChallengeService challengeService; 

    @GetMapping("/activity/emoji")
    public String showEmojiActivity(
            @AuthenticationPrincipal UserDetails userDetails, 
            @RequestParam(defaultValue = "0") int index, 
            Model model) {
        
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        List<EmojiChallenge> challenges = emojiRepository.findByLevel(user.getLevel());

        if (challenges.isEmpty() || index >= challenges.size()) {
            return "redirect:/dashboard?finish=true";
        }

        EmojiChallenge currentChallenge = challenges.get(index);
        ChallengeResponseDTO challengeDTO = challengeService.mapToDTO(currentChallenge);
        
        Collections.shuffle(challengeDTO.options());

        model.addAttribute("challenge", challengeDTO);
        model.addAttribute("currentIndex", index);
        model.addAttribute("nextIndex", index + 1);
        model.addAttribute("totalChallenges", challenges.size());
        
        return "activities/emoji";
    }
}