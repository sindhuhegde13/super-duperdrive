package com.nanodegree.superduperdrive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Login controller.
 */
@Controller
public class LoginController {

    /**
     * Login view string.
     *
     * @param model the model
     * @param value the value
     * @return the string
     */
    @GetMapping("/login")
    public String loginView(Model model,String value) {
        model.addAttribute("param",value);
        return "login";
    }

    /**
     * Log out view string.
     *
     * @param model the model
     * @return the string
     */
    @PostMapping("/logout")
    public String logOutView(Model model) {
        return "redirect:/login?logout=" + true;
    }

}