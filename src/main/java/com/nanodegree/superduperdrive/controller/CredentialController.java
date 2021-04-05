package com.nanodegree.superduperdrive.controller;

import com.nanodegree.superduperdrive.model.CredentialForm;
import com.nanodegree.superduperdrive.model.Credentials;
import com.nanodegree.superduperdrive.services.CredentialsService;
import com.nanodegree.superduperdrive.services.EncryptionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * The type Credential controller.
 */
@Controller
@RequestMapping("/credentials")
public class CredentialController {

    private CredentialsService credentialsService;
    private EncryptionService encryptionService;

    /**
     * Instantiates a new Credential controller.
     *
     * @param credentialsService the credentials service
     * @param encryptionService  the encryption service
     */
    public CredentialController(CredentialsService credentialsService, EncryptionService encryptionService) {
        this.credentialsService = credentialsService;
        this.encryptionService = encryptionService;
    }

    /**
     * Add credential string.
     *
     * @param authentication the authentication
     * @param credForm       the cred form
     * @param model          the model
     * @return the string
     */
    @PostMapping("/addcred")
    public String addCredential(Authentication authentication, @ModelAttribute("credForm")CredentialForm credForm, Model model) {
        String username = authentication.getName();
        String credUsername = credForm.getUserName();
        String credUrl = credForm.getUrl();
        String credPassword = credForm.getPassword();
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedKey = Base64.getEncoder().encodeToString(salt);
        String encryptedPassword = encryptionService.encryptValue(credPassword,encodedKey);
        Integer id = credForm.getCredentialId();
        if(id == null || id.toString().isEmpty()) {
            credentialsService.addCred(credUrl,username,credUsername,encodedKey,encryptedPassword);
        }
        else {
            credentialsService.updateCred(id,credUrl,credUsername,encodedKey,encryptedPassword);
        }
        model.addAttribute("result","success");
        return "result";
    }

    /**
     * Delete credential string.
     *
     * @param credId the cred id
     * @param model  the model
     * @return the string
     */
    @GetMapping("/deletecred")
    public String deleteCredential(@RequestParam(required = false, name = "credentialId") Integer credId, Model model) {
        credentialsService.deleteCred(credId);
        model.addAttribute("result","success");
        return "result";
    }
}