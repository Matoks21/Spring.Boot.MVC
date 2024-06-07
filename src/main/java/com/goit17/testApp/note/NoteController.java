package com.goit17.testApp.note;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/note/list")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "note/list";
    }

    @PostMapping("/note/delete")
    public String deleteNote(@RequestParam Long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/note/edit")
    public String editNoteForm(@RequestParam Long id, Model model) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "note/edit";
    }

    @PostMapping("/note/edit")
    public String editNote(Note note) {
        noteService.update(note);
        return "redirect:/note/list";
    }
    @GetMapping("/note/add")
    public String addNoteForm(Model model) {
        model.addAttribute("note", new Note());
        return "note/add";
    }

    @PostMapping("/note/add")
    public String addNote(@ModelAttribute Note note) {
        noteService.add(note);
        return "redirect:/note/list";
    }
}