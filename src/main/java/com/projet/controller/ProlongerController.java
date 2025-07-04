package com.projet.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.entity.*;
import com.projet.service.*;

@Controller
@RequestMapping("/prolonger")
public class ProlongerController 
{
    @Autowired
    private RetourLivreService retourLivreService;
    @Autowired
    private AdherentService adherentService;
    @Autowired
    private ExemplaireService exemplaireService;
    @Autowired
    private PretService pretService;
    @Autowired
    private HistoriquesPenalisationService historiqueService;
    @Autowired
    private StatutPretService statutPretService;
    @Autowired
    private StatutQuotaService statutQuotaService;

    @GetMapping("/home")
    public String home(Model model) 
    {
        model.addAttribute("listePrets", pretService.findAllWithAdherentAndExemplaireAndLivre());
        return "prolonger/home";
    }

}