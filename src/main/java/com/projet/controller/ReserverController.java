package com.projet.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.aot.hint.annotation.Reflective;
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
    private StatutPretService statutPretService;
    @Autowired
    private StatutQuotaService statutQuotaService;
    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private LivreTypeAdherentService livreTypeAdherentService;
    @Autowired
    private HistoriquesPenalisationService historiquesPenalisationService;
    @Autowired
    private ReservationService reservationService;

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

    @GetMapping("/confirmation")
    public String confirmation(
        @RequestParam("date_retour") String date_retour,
        Model model,
        jakarta.servlet.http.HttpSession session
    )
    {
        // Conversion en Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateRetour = null;
        try {
            dateRetour = sdf.parse(date_retour);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            model.addAttribute("message", "Erreur de format de date");
            model.addAttribute("messageType", "error");
            return "reserver/home";
        }

        Integer idAdherent = (Integer) session.getAttribute("id_adherent");
        Integer idExemplaire = (Integer) session.getAttribute("id_exemplaire");
        Date dateReservation = (Date) session.getAttribute("date_reservation");

        // REGLES DE GESTION
        // Verif date pret invalide
        if (dateReservation == null || dateRetour == null || dateReservation.after(dateRetour)) 
        {
            model.addAttribute("message", "Date de reservation ou de retour invalide");
            model.addAttribute("messageType", "error");
            return "reserver/home";
        }

        // Verif date de reservation et de retour
        Pret p = pretService.findTopByExemplaireIdOrderByIdDesc(idAdherent);
        Date date1 = p.getDate_pret();
        Date date2 = p.getDate_retour_prevu();
        if (dateReservation.before(date1) || dateReservation.before(date2))
        {
            model.addAttribute("message", "La date de reservation est invalide");
            model.addAttribute("messageType", "error");
            return "reserver/home";
        }
        if (dateRetour.before(date2))
        {
            model.addAttribute("message", "La date de retour prevu est invalide");
            model.addAttribute("messageType", "error");
            return "reserver/home";
        }

        // Activite de l'adherent
        Inscription inscription = inscriptionService.findByAdherentId(idAdherent);
        if (inscription == null || !inscription.isActive(dateReservation) || !inscription.isActive(dateRetour)) 
        {
            model.addAttribute("message", "Adherent inactif");
            model.addAttribute("messageType", "error");
            return "reserver/home";
        }

        // Verif nb quota
        StatutQuota statutQuota = statutQuotaService.findTopByAdherentIdOrderByIdDesc(idAdherent).orElse(null);
        if (statutQuota != null && statutQuota.getQuota() <= 0) 
        {
            model.addAttribute("message", "Quota de pret atteint pour cet adherent");
            model.addAttribute("messageType", "error");
            return "reserver/home";
        }

        // Autorisation du livre selon le type d'adherent
        LivreTypeAdherent livreTypeAdherent = livreTypeAdherentService.findByLivreAndTypeAdherent(
            exemplaireService.findById(idExemplaire).orElse(null).getLivre(),
            adherentService.findById(idAdherent).orElse(null).getType_adherent()
        ).orElse(null);
        if (livreTypeAdherent == null) 
        {
            model.addAttribute("message", "Le livre n'est pas autorise pour ce type d'adherent");
            model.addAttribute("messageType", "error");
            return "reserver/home";
        }

        // Verif penalisation
        HistoriquesPenalisation historique = historiquesPenalisationService.findTopByAdherentIdOrderByIdDesc(idAdherent).orElse(null);
        if (historique != null && historique.isPenalised(dateReservation) && historique.isPenalised(dateRetour)) 
        {
            model.addAttribute("message", "L'adherent est actuellement penalise et ne peut pas emprunter de livres");
            model.addAttribute("messageType", "error");
            return "reserver/home";
        }

        // /////////////////

        // Enregistrement du pret
        Exemplaire exemplaire = exemplaireService.findById(idExemplaire).orElse(null);
        Adherent adherent = adherentService.findById(idAdherent).orElse(null);
        Pret pret = new Pret();
        pret.setExemplaire(exemplaire);
        pret.setAdherent(adherent);
        pret.setDate_pret(dateReservation);
        pret.setDate_retour_prevu(dateRetour);
        pretService.save(pret);

        // Enregistrement dans reservation
        Reservation reservation = new Reservation();
        reservation.setAdherent(adherent);
        reservation.setExemplaire(exemplaire);
        reservation.setDate_reservation(dateReservation);
        reservationService.save(reservation);

        // Mise a jour du statut de pret
        StatutPret statutPret = new StatutPret();
        statutPret.setStatut(0); // En cours
        statutPret.setDaty(dateReservation);
        statutPret.setPret(pret);
        statutPretService.save(statutPret);

        // Mise a jour du quota
        if (statutQuota != null) 
        {
            StatutQuota sq = new StatutQuota();
            sq.setAdherent(adherent);
            sq.setQuota(statutQuota.getQuota() - 1);
            sq.setDaty(dateReservation);
            statutQuotaService.save(sq);
        } 
        else 
        {
            model.addAttribute("message", "Statut de quota non trouve pour l'adherent");
            model.addAttribute("messageType", "error");
            return "reserver/home";
        }

        // Mise a jour nb exemplaire
        Exemplaire exe = exemplaireService.findById(idExemplaire).orElse(null);
        Livre livre = exe.getLivre();
        Exemplaire exemp = exemplaireService.findTopByLivreIdOrderByIdDesc(livre.getId()).orElse(null);
        Exemplaire e = new Exemplaire();
        e.setDaty(dateReservation);
        e.setLivre(livre); 
        e.setNb_exemplaires(exemp.getNb_exemplaires() - 1);
        exemplaireService.save(e);


        model.addAttribute("message", "Reservation enregistree avec succes");
        model.addAttribute("messageType", "success");
        return "reserver/home";

    }
}