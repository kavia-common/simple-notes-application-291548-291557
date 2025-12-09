package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * PUBLIC_INTERFACE
 * DTOs for Authentication API.
 */
public class AuthDtos {

    public static class SignupRequest {
        @Schema(description = "Unique username", example = "alice", required = true)
        @NotBlank(message = "username is required")
        public String username;

        @Schema(description = "Password", example = "s3cret", required = true)
        @NotBlank(message = "password is required")
        public String password;
    }

    public static class LoginRequest {
        @Schema(description = "Username", example = "alice", required = true)
        @NotBlank(message = "username is required")
        public String username;

        @Schema(description = "Password", example = "s3cret", required = true)
        @NotBlank(message = "password is required")
        public String password;
    }

    public static class AuthResponse {
        public String message;

        public AuthResponse(String message) {
            this.message = message;
        }
    }
}
