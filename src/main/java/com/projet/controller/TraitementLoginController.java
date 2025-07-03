package com.projet.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.entity.*;
import com.projet.service.*;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/traitement-login")
public class TraitementLoginController 
{
    @Autowired
    private AdminService adminService;
    @Autowired
    private BibliothecaireService bibliothecaireService;
    @Autowired
    private AdherentService adherentService;
    @Autowired
    private TypeAdherentService typeAdherentService;
    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private StatutQuotaService statutQuotaService;


    @PostMapping("/admin")
    public String loginAdmin(
        @RequestParam String pseudo, 
        @RequestParam String mdp, 
        HttpSession session, 
        Model model
    ) 
    {
        Admin admin = adminService.findByPseudoAndMdp(pseudo, mdp);
        if (admin != null) 
        {
            session.setAttribute("admin", admin);
            return "redirect:/admin/home";
        } else {
            model.addAttribute("error", "Identifiants incorrects");
            return "login-admin";
        }
    }

    @PostMapping("/bibliothecaire")
    public String loginBibliothecaire(
        @RequestParam String pseudo, 
        @RequestParam String mdp, 
        HttpSession session, 
        Model model
    ) 
    {
        Bibliothecaire bibliothecaire = bibliothecaireService.findByPseudoAndMdp(pseudo, mdp);
        if (bibliothecaire != null) 
        {
            session.setAttribute("bibliothecaire", bibliothecaire);
            return "redirect:/bibliothecaire/home";
        } else {
            model.addAttribute("error", "Identifiants incorrects");
            return "login-bibliothecaire";
        }
    }

    @PostMapping("/adherent")
    public String loginAdherent(
        @RequestParam String nom, 
        @RequestParam String mdp, 
        HttpSession session, 
        Model model
    ) 
    {
        Adherent adherent = adherentService.findByNomAndMdp(nom, mdp);
        if (adherent != null) 
        {
            session.setAttribute("adherent", adherent);
            return "redirect:/adherent/home";
        } else {
            model.addAttribute("error", "Identifiants incorrects");
            return "login-adherent";
        }
    }

    @PostMapping("/inscription")
    public String inscriptionAdherent(
        @RequestParam( "nom") String nom,
        @RequestParam("mdp") String mdp,
        @RequestParam("typeAdherent") int typeAdherentId,
        @RequestParam("dateInscription") String dateInscription,
        @RequestParam("dateExpiration") String dateExpiration
    )
    {
        TypeAdherent typeAdherent = typeAdherentService.findById(typeAdherentId).orElse(null);
        
        // save dans ADHERENT
        Adherent adherent = new Adherent();
        adherent.setNom(nom);
        adherent.setMdp(mdp);
        adherent.setType_adherent(typeAdherent);
        adherentService.save(adherent);

        // save dans STATUT_QUOTA
        StatutQuota statutQuota = new StatutQuota();
        statutQuota.setAdherent(adherent);
        statutQuota.setDaty(Date.from(java.time.LocalDate.now().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
        statutQuota.setQuota(typeAdherent.getQuota());
        statutQuotaService.save(statutQuota);

        // Conversion en Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateIns = null;
        Date dateExp = null;
        try {
            dateIns = sdf.parse(dateInscription);
            dateExp = sdf.parse(dateExpiration);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return "/";
        }

        // save dans INSCRIPTION
        Inscription inscription = new Inscription();
        inscription.setAdherent(adherent);
        inscription.setDate_inscription(dateIns);
        inscription.setDate_expiration(dateExp);
        inscriptionService.save(inscription);
        
        return "redirect:/bibliothecaire/home";
    }
}

    