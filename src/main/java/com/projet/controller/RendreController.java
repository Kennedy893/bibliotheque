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
@RequestMapping("/rendre")
public class RendreController 
{
    @Autowired
    private RetourLivreService rendreService;
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
        // model.addAttribute("listePrets", pretService.findAll());
        model.addAttribute("listePrets", pretService.findAllWithAdherentAndExemplaireAndLivre());
        return "rendre/home";
    }

    @PostMapping("/save")
    public String save(
        @RequestParam("id_pret") int idPret,
        @RequestParam("date_retour") String dateRetour,
        Model model
    )
    {
        // Conversion en Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateRetourDate = null;
        try {
            dateRetourDate = sdf.parse(dateRetour);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Erreur de format de date");
            model.addAttribute("messageType", "error");
            model.addAttribute("listePrets", pretService.findAllWithAdherentAndExemplaireAndLivre());
            return "rendre/home";
        }

        // Recuperation du prêt avec toutes les relations necessaires
        Pret pret = pretService.findByIdWithAdherentAndTypeAdherent(idPret);

        // REGLES DE GESTION
        RetourLivre retourLivre = rendreService.findByPret(pret);
        if (retourLivre != null) 
        {
            model.addAttribute("message", "Le livre a dejà ete rendu.");
            model.addAttribute("messageType", "error");
            model.addAttribute("listePrets", pretService.findAllWithAdherentAndExemplaireAndLivre());
            return "rendre/home";
        }

        if (pret == null) 
        {
            model.addAttribute("message", "Prêt introuvable");
            model.addAttribute("messageType", "error");
            model.addAttribute("listePrets", pretService.findAllWithAdherentAndExemplaireAndLivre());
            return "rendre/home";
        }

        // MAJ Penalisation
        Date retourPrevu = pret.getDate_retour_prevu();
        if (retourPrevu.before(dateRetourDate)) 
        {
            HistoriquesPenalisation dernierePenalisation = historiqueService.findByAdherentId(pret.getAdherent().getId()).orElse(null);
            int surplus_jours = 0;
            if (dernierePenalisation != null && dernierePenalisation.getDate_fin().after(dateRetourDate)) 
            {
                // raha aoriananle penalisation vaovao ny faranle taloha
                surplus_jours = (int) ((dernierePenalisation.getDate_fin().getTime() - dateRetourDate.getTime()) / (1000 * 60 * 60 * 24));
            }

            int joursRetard = (int) ((dateRetourDate.getTime() - retourPrevu.getTime()) / (1000 * 60 * 60 * 24));
            model.addAttribute("alerte", "Le retour du livre est retarde de " + joursRetard + " jour(s).");
 
            HistoriquesPenalisation penalisation = new HistoriquesPenalisation();
            penalisation.setAdherent(pret.getAdherent());
            penalisation.setDate_debut(dateRetourDate);
            long dateFinMillis = dateRetourDate.getTime() + (joursRetard * 24L * 60 * 60 * 1000) + (surplus_jours * 24L * 60 * 60 * 1000); // miampy anle jours de retard
            Date current_fin = new Date(dateFinMillis);
            penalisation.setDate_fin(current_fin); 
            historiqueService.save(penalisation);
        }

        // MAJ Statut_pret
        StatutPret statutPret = new StatutPret();
        statutPret.setId(1); // 1 = Rendu
        statutPret.setPret(pret);
        statutPret.setDaty(dateRetourDate);
        statutPretService.save(statutPret);

        // MAJ Quota
        StatutQuota statutQuota = new StatutQuota();
        statutQuota.setDaty(dateRetourDate);
        statutQuota.setAdherent(pret.getAdherent());
        statutQuota.setQuota(pret.getAdherent().getType_adherent().getQuota() + 1); 
        statutQuotaService.save(statutQuota);


        // Enregistrement du retour
        RetourLivre rendre = new RetourLivre();
        rendre.setPret(pret);
        rendre.setDate_retour(dateRetourDate);
        rendreService.save(rendre);

        model.addAttribute("message", "Retour enregistre avec succes! ");
        model.addAttribute("messageType", "success");
        model.addAttribute("listePrets", pretService.findAllWithAdherentAndExemplaireAndLivre());
        return "rendre/home";
    }
}