package com.example.notesbackend.web;

import com.example.notesbackend.dto.NoteDtos;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PUBLIC_INTERFACE
 * Notes REST controller exposing CRUD endpoints.
 */
@RestController
@RequestMapping("/notes")
@Tag(name = "Notes", description = "CRUD operations for Notes")
public class NotesController {

    private final NoteService service;

    public NotesController(NoteService service) {
        this.service = service;
    }

    /**
     * PUBLIC_INTERFACE
     * List notes
     */
    @GetMapping
    @Operation(summary = "List notes", description = "Returns all notes.")
    public List<NoteDtos.Response> listNotes() {
        return service.list();
    }

    /**
     * PUBLIC_INTERFACE
     * Get note by id
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get note", description = "Returns a note by id.")
    public NoteDtos.Response getNote(@PathVariable Long id) {
        return service.get(id);
    }

    /**
     * PUBLIC_INTERFACE
     * Create a new note
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create note", description = "Creates a new note. title and content are required.")
    public NoteDtos.Response createNote(@Valid @RequestBody NoteDtos.CreateRequest request) {
        return service.create(request);
    }

    /**
     * PUBLIC_INTERFACE
     * Update an existing note
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update note", description = "Updates an existing note by id. title and content are required.")
    public NoteDtos.Response updateNote(@PathVariable Long id, @Valid @RequestBody NoteDtos.UpdateRequest request) {
        return service.update(id, request);
    }

    /**
     * PUBLIC_INTERFACE
     * Delete a note by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete note", description = "Deletes a note by id.")
    public void deleteNote(@PathVariable Long id) {
        service.delete(id);
    }
}
