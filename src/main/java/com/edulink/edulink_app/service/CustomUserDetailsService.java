package com.edulink.edulink_app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edulink.edulink_app.model.User;
import com.edulink.edulink_app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscamos al usuario por email en lugar de username
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + email));

        // Retornamos el objeto que Spring Security entiende
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail()) // Aquí usamos el email como identificador de sesión
                .password(user.getPassword())
                .roles("USER") // Puedes ajustar los roles según tu lógica
                .build();
    }
}