package com.edulink.edulink_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AnswerRequestDTO(
    @Schema(description = "ID del reto en la base de datos", example = "25") Long challengeId,
    @Schema(description = "Texto de la respuesta seleccionada por el estudiante", example = "Azul") String selectedOption
) {}
