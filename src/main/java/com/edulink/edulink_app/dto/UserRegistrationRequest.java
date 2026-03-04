package com.edulink.edulink_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequest(
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Schema(example = "Jose Perez")String username,

    @Email(message = "El formato del email no es válido")
    @NotBlank(message = "El email es obligatorio")
    @Schema(example = "jose@example.com")String email,

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Schema(example = "password123")String password,

    @NotBlank(message = "Debes seleccionar un nivel")
    @Schema(example = "GENERAL")String level
) {

}
