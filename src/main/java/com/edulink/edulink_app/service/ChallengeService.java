package com.edulink.edulink_app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.edulink.edulink_app.dto.ChallengeResponseDTO;
import com.edulink.edulink_app.model.Challenge;
import com.edulink.edulink_app.model.EmojiChallenge;
import com.edulink.edulink_app.model.MatchChallenge;
import com.edulink.edulink_app.model.MusicChallenge;
import com.edulink.edulink_app.model.User;
import com.edulink.edulink_app.repository.ChallengeRepository;
import com.edulink.edulink_app.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;

    public ChallengeResponseDTO mapToDTO(Challenge challenge) {
        List<String> optionsList = new ArrayList<>(Arrays.asList(challenge.getOptions().split(",")));

        if (challenge instanceof MatchChallenge) {
            if (!optionsList.contains(challenge.getCorrectAnswer())) {
                optionsList.add(challenge.getCorrectAnswer());
            }
        }

        String mediaId = (challenge instanceof MusicChallenge m) ? m.getMediaId() : 
                        (challenge instanceof EmojiChallenge e) ? e.getMediaId() : null;
        
        Integer pauseTime = (challenge instanceof MusicChallenge m) ? m.getPauseTime() : null;
        Integer startTime = (challenge instanceof MusicChallenge m) ? m.getStartTime() : null;

        return new ChallengeResponseDTO(
            challenge.getId(),
            challenge.getType().name(),
            challenge.getLevel().name(),
            challenge.getTitle(),
            challenge.getQuestionText(),
            challenge.getCorrectAnswer(),
            optionsList, 
            mediaId,
            pauseTime,
            startTime
        );
    }

    @Transactional // Asegura que si algo falla, no se guarden puntos parciales
    public boolean verifyAndAwardPoints(Long challengeId, String userAnswer, User user) {
        // Buscamos el reto en la base de datos
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new NoSuchElementException("Reto no encontrado"));

        if (challenge.getCorrectAnswer().equalsIgnoreCase(userAnswer.trim())) {
            int pointsToAward = 10; 
            user.setTotalPoints(user.getTotalPoints() + pointsToAward);
            userRepository.save(user); 
            return true;
        }
        
        return false;
    }
}
