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
public class EmojiActivityController {

    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

    @GetMapping("/activity/emoji")
    public String showEmojiActivity(@AuthenticationPrincipal UserDetails userDetails, 
                                   @RequestParam(defaultValue = "0") int index, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Challenge> challenges = challengeRepository.findByTypeAndLevel(ActivityType.EMOJI, user.getLevel());

        if (challenges.isEmpty() || index >= challenges.size()) {
            return "redirect:/dashboard?finish=true";
        }

        Challenge currentChallenge = challenges.get(index);
        List<String> optionsList = Arrays.asList(currentChallenge.getOptions().split(","));
        Collections.shuffle(optionsList);

        model.addAttribute("challenge", currentChallenge);
        model.addAttribute("options", optionsList);
        model.addAttribute("nextIndex", index + 1);
        return "activities/emoji";
    }
}
