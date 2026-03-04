package com.edulink.edulink_app.dto;

import java.util.List;

public record ChallengeResponseDTO(
    Long id,
    String type,
    String level,
    String title,
    String questionText,
    String correctAnswer,
    List<String> options, 
    String mediaId,       
    Integer pauseTime,    
    Integer startTime
) {}
