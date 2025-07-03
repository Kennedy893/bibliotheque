package com.projet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bibliothecaire")
public class BibliothecaireController {
    @GetMapping("/home")
    public String home() {
        return "bibliothecaire/home";
    }
}
