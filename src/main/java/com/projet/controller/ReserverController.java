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
@RequestMapping("/reserver")
public class ReserverController
{
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
    @Autowired
    private ProlongementService prolongementService;
    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private LivreService livreService;

    @GetMapping("/home")
    public String home(Model model) 
    {
        model.addAttribute("listeAdherents", adherentService.findAll());
        List<Exemplaire> exemplaires = exemplaireService.findAllExemplairesIndisponibles();
        model.addAttribute("listeExemplaires", exemplaires);
        return "reserver/home";
    }

    @PostMapping("/stockParams")
    public String stockParams(
        @RequestParam("id_adherent") int idAdherent,
        @RequestParam("id_exemplaire") int idExemplaire,
        @RequestParam("date_reservation") String dateReservation,
        Model model,
        jakarta.servlet.http.HttpSession session
    )
    {
        // Conversion en Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateReserv = null;
        try {
            dateReserv = sdf.parse(dateReservation);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Erreur de format de date");
            model.addAttribute("messageType", "error");
            return "prolonger/home";
        }
        session.setAttribute("id_adherent", idAdherent);
        session.setAttribute("id_exemplaire", idExemplaire);
        session.setAttribute("date_reservation", dateReserv);
        model.addAttribute("listeExemplaires", exemplaireService.findAllExemplairesIndisponibles());
        return "login-bibliothecaire";
    }
}