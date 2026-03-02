package com.edulink.edulink_app.dto;

public record UserResponseDTO(
    Long id,
    String username,
    String email,
    String userLevel
) {}
