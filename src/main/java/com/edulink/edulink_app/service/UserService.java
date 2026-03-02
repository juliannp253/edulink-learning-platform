package com.edulink.edulink_app.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edulink.edulink_app.dto.UserRegistrationRequest;
import com.edulink.edulink_app.exception.UserAlreadyExistsException;
import com.edulink.edulink_app.model.User;
import com.edulink.edulink_app.model.UserLevel;
import com.edulink.edulink_app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(UserRegistrationRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            // Al lanzar esto, el GlobalExceptionHandler lo atrapa automáticamente
            throw new UserAlreadyExistsException("Ese correo ya está registrado.");
        }
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setTotalPoints(0); 
        user.setLevel(UserLevel.valueOf(request.level().toUpperCase()));
        
        userRepository.save(user);
    }
}
