package com.goit17.testApp.note;

import com.goit17.testApp.repo.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        List<Note> notes = new ArrayList<>();
        when(noteRepository.findAll()).thenReturn(notes);

        List<Note> result = noteService.listAll();

        assertEquals(notes, result);
    }

    @Test
    void testAdd() {
        Note note = new Note();
        when(noteRepository.save(note)).thenReturn(note);

        Note result = noteService.add(note);

        assertEquals(note, result);
    }

    @Test
    void testDeleteById() {
        long id = 1L;
        doNothing().when(noteRepository).deleteById(id);

        noteService.deleteById(id);

        verify(noteRepository, times(1)).deleteById(id);
    }

    @Test
    void testUpdate() {
        Note note = new Note();
        when(noteRepository.save(note)).thenReturn(note);

        noteService.update(note);

        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void testGetById() {
        long id = 1L;
        Note note = new Note();
        when(noteRepository.findById(id)).thenReturn(Optional.of(note));

        Note result = noteService.getById(id);

        assertEquals(note, result);
    }
}
