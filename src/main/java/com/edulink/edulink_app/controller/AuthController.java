package com.edulink.edulink_app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.edulink.edulink_app.dto.UserRegistrationRequest;
import com.edulink.edulink_app.exception.UserAlreadyExistsException;
import com.edulink.edulink_app.model.UserLevel;
import com.edulink.edulink_app.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Controlador para el registro e inicio de sesión de usuarios")
public class AuthController {

    private final UserService userService;

    @ModelAttribute("levels")
    public UserLevel[] getLevels() {
        return UserLevel.values();
    }

    @Operation(summary = "Mostrar formulario de registro", 
               description = "Retorna la vista 'register' con un objeto de solicitud de registro vacío.")
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationRequest("", "", "", ""));
        return "register";
    }

    @Operation(summary = "Procesar el registro de usuario", 
               description = "Valida los datos de entrada y registra al usuario. Redirige al login si tiene éxito.")
    @ApiResponse(responseCode = "302", description = "Redirección exitosa al login o retorno al formulario por errores")
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationRequest registrationRequest,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            return "register"; 
        }
        try {
            userService.saveUser(registrationRequest);
            return "redirect:/login?success";
        }   catch(UserAlreadyExistsException ex) {
            model.addAttribute("error", ex.getMessage());
            return "register";
        }
    }

    @Operation(summary = "Mostrar formulario de login", 
               description = "Retorna la vista 'login' para la autenticación de usuarios.")
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
