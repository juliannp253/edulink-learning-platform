package com.edulink.edulink_app.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Indica que esta clase escucha errores de TODA la app
public class GlobalExceptionHandler {

    // Este método se activa cuando alguien lanza un "UserAlreadyExistsException"
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserExists(UserAlreadyExistsException ex, Model model) {
        // En lugar de una página de error, lo mandamos de vuelta al registro con el mensaje
        model.addAttribute("error", ex.getMessage());
        return "register"; 
    }

    // Para atrapar errores genéricos para que la app nunca se caiga
    /*@ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception ex, Model model) {
        model.addAttribute("error", "Ocurrió un error inesperado. Inténtalo más tarde.");
        return "error"; // Una página personalizada creada para errores
    }*/
}