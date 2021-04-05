package com.nanodegree.superduperdrive.controller;

import com.nanodegree.superduperdrive.model.CredentialForm;
import com.nanodegree.superduperdrive.model.FileForm;
import com.nanodegree.superduperdrive.model.NoteForm;
import com.nanodegree.superduperdrive.services.CredentialsService;
import com.nanodegree.superduperdrive.services.EncryptionService;
import com.nanodegree.superduperdrive.services.FilesService;
import com.nanodegree.superduperdrive.services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Home controller.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    private FilesService filesService;
    private NotesService notesService;
    private CredentialsService credentialsService;
    private EncryptionService encryptionService;

    /**
     * Instantiates a new Home controller.
     *
     * @param filesService       the files service
     * @param notesService       the notes service
     * @param credentialsService the credentials service
     * @param encryptionService  the encryption service
     */
    public HomeController(FilesService filesService, NotesService notesService, CredentialsService credentialsService, EncryptionService encryptionService) {
        this.filesService = filesService;
        this.notesService = notesService;
        this.credentialsService = credentialsService;
        this.encryptionService = encryptionService;
    }

    /**
     * Gets home page.
     *
     * @param authentication the authentication
     * @param newFile        the new file
     * @param newNote        the new note
     * @param credForm       the cred form
     * @param model          the model
     * @return the home page
     */
    @GetMapping
    public String getHomePage(Authentication authentication, @ModelAttribute("fileForm") FileForm newFile, @ModelAttribute("noteForm") NoteForm newNote, @ModelAttribute("credForm") CredentialForm credForm, Model model) {
        model.addAttribute("allFiles",filesService.getFilesByName(authentication.getName()));
        model.addAttribute("allNotes",notesService.getNotesByUserName(authentication.getName()));
        model.addAttribute("allCredentials",credentialsService.getCredentials(authentication.getName()));
        model.addAttribute("encryptionService",encryptionService);
        return "home";
    }

}
