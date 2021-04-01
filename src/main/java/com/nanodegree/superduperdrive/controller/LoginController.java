package com.nanodegree.superduperdrive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginView(Model model,String value) {
        model.addAttribute("param",value);
        return "login";
    }

    @GetMapping("/logout")
    public String logOutView(Model model) {
        return "redirect:/login?logout=" + true;
    }

}