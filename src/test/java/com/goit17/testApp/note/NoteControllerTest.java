package com.goit17.testApp.note;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NoteControllerTest {

    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListNotes() {
        Model model = mock(Model.class);
        List<Note> notes = new ArrayList<>();
        when(noteService.listAll()).thenReturn(notes);

        String result = noteController.listNotes(model);

        assertEquals("note/list", result);
        verify(model, times(1)).addAttribute("notes", notes);
    }

    @Test
    void testDeleteNote() {
        long id = 1L;
        doNothing().when(noteService).deleteById(id);

        String result = noteController.deleteNote(id);

        assertEquals("redirect:/note/list", result);
        verify(noteService, times(1)).deleteById(id);
    }

    @Test
    void testEditNoteForm() {
        long id = 1L;
        Model model = mock(Model.class);
        Note note = new Note();
        when(noteService.getById(id)).thenReturn(note);

        String result = noteController.editNoteForm(id, model);

        assertEquals("note/edit", result);
        verify(model, times(1)).addAttribute("note", note);
    }

    @Test
    void testEditNote() {
        Note note = new Note();
        doNothing().when(noteService).update(note);

        String result = noteController.editNote(note);

        assertEquals("redirect:/note/list", result);
        verify(noteService, times(1)).update(note);
    }

    @Test
    void testAddNoteForm() {
        Model model = mock(Model.class);

        String result = noteController.addNoteForm(model);

        assertEquals("note/add", result);
        verify(model, times(1)).addAttribute("note", new Note());
    }

    @Test
    void testAddNote() {
        Note note = new Note();
        when(noteService.add(note)).thenReturn(note);

        String result = noteController.addNote(note);

        assertEquals("redirect:/note/list", result);
        verify(noteService, times(1)).add(note);
    }
}
