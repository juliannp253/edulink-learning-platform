package com.edulink.edulink_app.controller;

import java.util.NoSuchElementException;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.edulink.edulink_app.model.User;
import com.edulink.edulink_app.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Controlador para la gestión de la interfaz principal del usuario")
public class DashboardController {

    private final UserRepository userRepository;

    @Operation(
        summary = "Visualizar el panel principal",
        description = "Carga la información del estudiante (puntos, nivel y nombre de usuario) utilizando el correo electrónico de la sesión activa."
    )
    @ApiResponse(responseCode = "200", description = "Vista del dashboard cargada correctamente")
    @ApiResponse(responseCode = "404", description = "El usuario no existe en la base de datos")
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new NoSuchElementException("No se encontró el usuario con el correo: " + userDetails.getUsername()));
        model.addAttribute("username", user.getUsername());
        model.addAttribute("level", user.getLevel().toString());
        model.addAttribute("points", user.getTotalPoints());
        
        return "dashboard";
    }
}