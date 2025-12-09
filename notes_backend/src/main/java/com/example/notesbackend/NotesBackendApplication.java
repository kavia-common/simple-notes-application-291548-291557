package com.example.notesbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PUBLIC_INTERFACE
 * Spring Boot entrypoint.
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Simple Notes API",
                version = "0.1.0",
                description = "REST API for managing notes with simple authentication. Visit /docs for Swagger UI."
        )
)
public class NotesBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotesBackendApplication.class, args);
    }
}
