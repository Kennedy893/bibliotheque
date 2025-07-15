package com.projet.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.entity.*;
import com.projet.service.*;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/webapi")
public class WebApiController 
{
    @Autowired 
    private LivreService livreService;
    @Autowired 
    private ExemplaireService exemplaireService;

    @GetMapping("/livre/{id}")
    public ResponseEntity<LivreDTO> getInfosLivreById(@PathVariable int id) 
    {
        Livre livre = livreService.findByIdWithGenres(id).orElse(null);
        if (livre == null) 
        {
            return ResponseEntity.notFound().build();
        }

        int nbExemplaires = exemplaireService
                .findTopByLivreIdOrderByIdDesc(id)
                .map(Exemplaire::getNb_exemplaires)
                .orElse(0); // ou -1 si tu veux dire "aucun exemplaire enregistré"

        LivreDTO dto = new LivreDTO(livre, nbExemplaires);
        return ResponseEntity.ok(dto);
    }

}