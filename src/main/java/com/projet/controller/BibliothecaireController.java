package com.projet.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.projet.service.ExemplaireService;
import com.projet.service.PretService;

@Controller
@RequestMapping("/bibliothecaire")
public class BibliothecaireController 
{
    @Autowired
    private PretService pretService;
    @Autowired 
    private ExemplaireService exemplaireService;
    
    @GetMapping("/home")
    public String home() {
        return "bibliothecaire/home";
    }

    @GetMapping("/confirmation-prolongement")
    public String confirmationProlongement(jakarta.servlet.http.HttpSession session, Model model) 
    {
        Integer idPret = (Integer) session.getAttribute("id_pret");
        Integer surplusJours = (Integer) session.getAttribute("surplus_jours");
        model.addAttribute("id_pret", idPret);
        model.addAttribute("surplus", surplusJours);
        model.addAttribute("listePrets", pretService.findAllWithAdherentAndExemplaireAndLivre());
        return "bibliothecaire/confirmation-prolongement";
    }

    @GetMapping("/confirmation-reservation")
    public String confirmationReservation(jakarta.servlet.http.HttpSession session, Model model) 
    {
        Integer idAdherent = (Integer) session.getAttribute("id_adherent");
        Integer idExemplaire = (Integer) session.getAttribute("id_exemplaire");
        Date dateReservation = (Date) session.getAttribute("date_reservation");
        model.addAttribute("id_adherent", idAdherent);
        model.addAttribute("id_exemplaire", idExemplaire);
        model.addAttribute("date_reservation", dateReservation);
        model.addAttribute("listeExemplaires", exemplaireService.findAllExemplairesIndisponibles());
        return "bibliothecaire/confirmation-reservation";
    }
}
