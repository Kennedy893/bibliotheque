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
@RequestMapping("/preter")
public class PreterController 
{
    @Autowired
    private TypeAdherentService typeAdherentService;
    @Autowired
    private AdherentService adherentService;
    @Autowired
    private ExemplaireService exemplaireService;
    @Autowired
    private PretService pretService;
    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private StatutQuotaService statutQuotaService;
    @Autowired
    private LivreTypeAdherentService livreTypeAdherentService;
    @Autowired
    private HistoriquesPenalisationService historiquesPenalisationService;
    @Autowired
    private StatutPretService statutPretService;

    @GetMapping("/home")
    public String home(Model model) 
    {
        model.addAttribute("listeAdherents", adherentService.findAll());
        List<Exemplaire> exemplaires = exemplaireService.findAllWithLivre();
        model.addAttribute("listeExemplaires", exemplaires);
        return "preter/home";
    }

    @PostMapping("/save")
    public String savePret(
        @RequestParam("id_exemplaire") int idExemplaire,
        @RequestParam("id_adherent") int idAdherent,
        @RequestParam("date_pret") String datePret,
        @RequestParam("date_retour_prevu") String dateRetourPrevu,
        Model model
    ) 
    {
        // Conversion en Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateP = null;
        Date dateR = null;
        try {
            dateP = sdf.parse(datePret);
            dateR = sdf.parse(dateRetourPrevu);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            model.addAttribute("message", "Erreur de format de date");
            model.addAttribute("messageType", "error");
            return "preter/home";
        }

        // REGLES DE GESTION
        if (dateP == null || dateR == null || dateP.after(dateR)) 
        {
            model.addAttribute("message", "Date de pret ou de retour invalide");
            model.addAttribute("messageType", "error");
            return "preter/home";
        }

        Inscription inscription = inscriptionService.findByAdherentId(idAdherent);
        if (inscription == null || !inscription.isActive(dateP)) 
        {
            model.addAttribute("message", "Adherent inactif");
            model.addAttribute("messageType", "error");
            return "preter/home";
        }

        StatutQuota statutQuota = statutQuotaService.findByAdherentId(idAdherent).orElse(null);
        if (statutQuota != null && statutQuota.getQuota() <= 0) 
        {
            model.addAttribute("message", "Quota de pret atteint pour cet adherent");
            model.addAttribute("messageType", "error");
            return "preter/home";
        }

        LivreTypeAdherent livreTypeAdherent = livreTypeAdherentService.findByLivreAndTypeAdherent(
            exemplaireService.findById(idExemplaire).orElse(null).getLivre(),
            adherentService.findById(idAdherent).orElse(null).getType_adherent()
        ).orElse(null);
        if (livreTypeAdherent == null) 
        {
            model.addAttribute("message", "Le livre n'est pas autorise pour ce type d'adherent");
            model.addAttribute("messageType", "error");
            return "preter/home";
        }

        HistoriquesPenalisation historique = historiquesPenalisationService.findByAdherentId(idAdherent).orElse(null);
        if (historique != null && historique.isPenalised()) 
        {
            model.addAttribute("message", "L'adherent est actuellement penalise et ne peut pas emprunter de livres");
            model.addAttribute("messageType", "error");
            return "preter/home";
        }

        // Mise a jour du quota
        if (statutQuota != null) 
        {
            statutQuota.setQuota(statutQuota.getQuota() - 1);
            statutQuotaService.save(statutQuota);
        } 
        else 
        {
            model.addAttribute("message", "Statut de quota non trouve pour l'adherent");
            model.addAttribute("messageType", "error");
            return "preter/home";
        }

        // Enregistrement du pret
        Exemplaire exemplaire = exemplaireService.findById(idExemplaire).orElse(null);
        Adherent adherent = adherentService.findById(idAdherent).orElse(null);

        Pret pret = new Pret();
        if (exemplaire == null || adherent == null) 
        {
            model.addAttribute("message", "Exemplaire ou Adherent non trouve");
            model.addAttribute("messageType", "error");
            return "preter/home";
        }
        pret.setExemplaire(exemplaire);
        pret.setAdherent(adherentService.findById(idAdherent).orElse(null));
        pret.setDate_pret(dateP);
        pret.setDate_retour_prevu(dateR);
        pretService.save(pret);

        // Mise a jour du statut de pret
        StatutPret statutPret = new StatutPret();
        statutPret.setStatut(0); // En cours
        statutPret.setDaty(dateP);
        statutPret.setPret(pret);
        statutPretService.save(statutPret);


        model.addAttribute("message", "Pret enregistre avec succes");
        model.addAttribute("messageType", "success");
        return "preter/home";
    }
}