package com.nanodegree.superduperdrive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Result controller.
 */
@Controller
@RequestMapping("/result")
public class ResultController {

    /**
     * Gets result page.
     *
     * @return the result page
     */
    @GetMapping()
    public String getResultPage() {
        return "result";
    }
}
