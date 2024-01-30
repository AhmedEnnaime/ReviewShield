package com.youcode.reviewshield.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("Name", SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }

}
