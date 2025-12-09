package com.example.notesbackend.repository;

import com.example.notesbackend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PUBLIC_INTERFACE
 * Repository interface for Note operations.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
