package com.example.notesbackend.web;

import com.example.notesbackend.dto.AuthDtos;
import com.example.notesbackend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * PUBLIC_INTERFACE
 * Authentication endpoints for signup and login.
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Authentication endpoints")
public class AuthController {

    private final AuthService auth;

    public AuthController(AuthService auth) {
        this.auth = auth;
    }

    /**
     * PUBLIC_INTERFACE
     * Signup endpoint
     */
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Signup", description = "Registers a new user with username and password.")
    public AuthDtos.AuthResponse signup(@Valid @RequestBody AuthDtos.SignupRequest request) {
        auth.signup(request);
        return new AuthDtos.AuthResponse("signup successful");
    }

    /**
     * PUBLIC_INTERFACE
     * Login endpoint
     */
    @PostMapping("/login")
    @Operation(summary = "Login", description = "Validates provided credentials. No token issued (stub).")
    public AuthDtos.AuthResponse login(@Valid @RequestBody AuthDtos.LoginRequest request) {
        auth.login(request);
        return new AuthDtos.AuthResponse("login successful");
    }
}
