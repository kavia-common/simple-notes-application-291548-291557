package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

/**
 * PUBLIC_INTERFACE
 * DTOs for Note API.
 */
public class NoteDtos {

    public static class CreateRequest {
        @Schema(description = "Title of the note", example = "Shopping List", required = true)
        @NotBlank(message = "title is required")
        public String title;

        @Schema(description = "Content/body of the note", example = "Milk, Bread, Eggs", required = true)
        @NotBlank(message = "content is required")
        public String content;
    }

    public static class UpdateRequest {
        @Schema(description = "Title of the note", example = "Updated title", required = true)
        @NotBlank(message = "title is required")
        public String title;

        @Schema(description = "Content/body of the note", example = "Updated content", required = true)
        @NotBlank(message = "content is required")
        public String content;
    }

    public static class Response {
        public Long id;
        public String title;
        public String content;
        public Instant createdAt;
        public Instant updatedAt;

        public Response(Long id, String title, String content, Instant createdAt, Instant updatedAt) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
    }
}
