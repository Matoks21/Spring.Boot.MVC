package com.goit17.testApp.repo;


import com.goit17.testApp.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
