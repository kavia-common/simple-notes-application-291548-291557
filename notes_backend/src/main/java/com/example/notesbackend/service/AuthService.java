package com.example.notesbackend.service;

import com.example.notesbackend.dto.AuthDtos;
import com.example.notesbackend.model.AppUser;
import com.example.notesbackend.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PUBLIC_INTERFACE
 * Service for simple user authentication logic (signup/login).
 */
@Service
@Transactional
public class AuthService {

    private final AppUserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AppUserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // PUBLIC_INTERFACE
    public void signup(AuthDtos.SignupRequest request) {
        if (userRepo.existsByUsername(request.username)) {
            throw new IllegalArgumentException("username already exists");
        }
        String encoded = passwordEncoder.encode(request.password);
        AppUser user = new AppUser(request.username, encoded);
        userRepo.save(user);
    }

    // PUBLIC_INTERFACE
    public void login(AuthDtos.LoginRequest request) {
        AppUser user = userRepo.findByUsername(request.username)
                .orElseThrow(() -> new IllegalArgumentException("invalid credentials"));
        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new IllegalArgumentException("invalid credentials");
        }
        // Stateless: no session or JWT generated. In a real app, return a token.
    }
}
