package com.nanodegree.superduperdrive.controller;

import com.nanodegree.superduperdrive.model.User;
import com.nanodegree.superduperdrive.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Signup controller.
 */
@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    /**
     * Instantiates a new Signup controller.
     *
     * @param userService the user service
     */
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Signup view string.
     *
     * @return the string
     */
    @GetMapping()
    public String signupView(){ return "signup";}

    /**
     * Signup user string.
     *
     * @param user  the user
     * @param model the model
     * @return the string
     */
    @PostMapping()
    public String signupUser(@ModelAttribute User user, Model model) {
        String signupError = null;

        if(!userService.isUserNameAvailable(user.getUsername())) {
            signupError = "This user already exists.";
        }

        if(signupError == null) {
            int rows = userService.createUser(user);
            if(rows < 0) {
                signupError = "There was a problem creating the user. Please try again.";
            }
        }

        if(signupError == null) {
            model.addAttribute("signupSuccess",true);
        }
        else {
            model.addAttribute("signupError",signupError);
        }
        return "signup";
    }
}