package com.nanodegree.superduperdrive.controller;

import com.nanodegree.superduperdrive.model.Files;
import com.nanodegree.superduperdrive.model.NoteForm;
import com.nanodegree.superduperdrive.model.Notes;
import com.nanodegree.superduperdrive.services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * The type Note controller.
 */
@Controller
@RequestMapping("/notes")
public class NoteController {

    private NotesService notesService;

    /**
     * Instantiates a new Note controller.
     *
     * @param notesService the notes service
     */
    public NoteController(NotesService notesService) {
        this.notesService = notesService;
    }

    /**
     * Add new note string.
     *
     * @param authentication the authentication
     * @param noteForm       the note form
     * @param model          the model
     * @return the string
     */
    @PostMapping("/addnote")
    public String addNewNote(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm, Model model) {
        String username = authentication.getName();
        if(noteForm.getNoteTitle().isEmpty() && noteForm.getNoteDescription().isEmpty()) {
            model.addAttribute("result","error");
            model.addAttribute("message","You entered an empty note.");
            return "result";
        }
        if(noteForm.getNoteId() == null || noteForm.getNoteId().toString().isEmpty()) {
            notesService.addNotes(username, noteForm.getNoteTitle(), noteForm.getNoteDescription());
        }
        else {
            notesService.updateNotes(noteForm.getNoteId(),noteForm.getNoteTitle(),noteForm.getNoteDescription());
        }
        model.addAttribute("allNotes",notesService.getNotesByUserName(username));
        model.addAttribute("result","success");
        return "result";
    }

    /**
     * Delete note string.
     *
     * @param noteId the note id
     * @param model  the model
     * @return the string
     */
    @GetMapping("/deletenote")
    public String deleteNote(@RequestParam(required = false, name = "noteId") Integer noteId, Model model) {
        Notes notes = notesService.getNotesById(noteId);
        notesService.deleteNotes(noteId);
        model.addAttribute("result","success");
        return "result";
    }
}
