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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
@Tag(name = "Progreso", description = "Endpoints para gestionar el avance y la puntuación de los estudiantes")
public class ProgressController {

    private final UserRepository userRepository;
    private final ChallengeService challengeService; 

    @Operation(
        summary = "Validar y procesar respuesta de reto",
        description = "Compara la opción seleccionada por el usuario con la respuesta correcta almacenada. Si coinciden, se otorga 10 puntos al usuario."
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Procesado exitosamente (independientemente de si la respuesta fue correcta o no)",
        content = @Content(schema = @Schema(example = "{\"correct\": true, \"newTotal\": 150, \"message\": \"¡Felicidades! +10 puntos\"}"))
    )
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado en la base de datos")
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