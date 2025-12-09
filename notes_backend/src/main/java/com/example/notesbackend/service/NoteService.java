package com.example.notesbackend.service;

import com.example.notesbackend.dto.NoteDtos;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * PUBLIC_INTERFACE
 * Service for managing notes. Provides CRUD operations.
 */
@Service
@Transactional
public class NoteService {

    private final NoteRepository repo;

    public NoteService(NoteRepository repo) {
        this.repo = repo;
    }

    // PUBLIC_INTERFACE
    public List<NoteDtos.Response> list() {
        return repo.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    // PUBLIC_INTERFACE
    public NoteDtos.Response get(Long id) {
        Note note = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Note not found"));
        return toDto(note);
    }

    // PUBLIC_INTERFACE
    public NoteDtos.Response create(NoteDtos.CreateRequest req) {
        Note note = new Note(req.title, req.content);
        Note saved = repo.save(note);
        return toDto(saved);
    }

    // PUBLIC_INTERFACE
    public NoteDtos.Response update(Long id, NoteDtos.UpdateRequest req) {
        Note note = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Note not found"));
        note.setTitle(req.title);
        note.setContent(req.content);
        Note saved = repo.save(note);
        return toDto(saved);
    }

    // PUBLIC_INTERFACE
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Note not found");
        }
        repo.deleteById(id);
    }

    private NoteDtos.Response toDto(Note n) {
        return new NoteDtos.Response(n.getId(), n.getTitle(), n.getContent(), n.getCreatedAt(), n.getUpdatedAt());
    }
}
