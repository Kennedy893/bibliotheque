package com.projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import com.projet.service.TypeAdherentService;

@Controller
@RequestMapping("/login")
public class LoginController 
{
    @Autowired
    private TypeAdherentService typeAdherentService;

    @GetMapping("/admin")
    public String loginAdmin() {
        return "login-admin";
    }

    @GetMapping("/bibliothecaire")
    public String loginBibliothecaire() {
        return "login-bibliothecaire";
    }

    @GetMapping("/adherent")
    public String loginAdherent() {
        return "login-adherent";
    }

    @GetMapping("/inscription")
    public String inscriptionAdherent(
        Model model
    ) 
    {
        model.addAttribute("typeAdherents", typeAdherentService.findAll());
        return "/adherent/inscription";
    }
}
    