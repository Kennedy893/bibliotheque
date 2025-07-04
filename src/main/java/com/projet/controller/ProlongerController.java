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
    // @Autowired
    // private RetourLivreService retourLivreService;
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

    @GetMapping("/home")
    public String home(Model model) 
    {
        model.addAttribute("listePrets", pretService.findAllWithAdherentAndExemplaireAndLivre());
        return "prolonger/home";
    }

    @PostMapping("/stockParams")
    public String stockParams(
        @RequestParam("id_pret") int idPret,
        @RequestParam("surplus_jours") int surplusJours,
        Model model
    )
    {
        model.addAttribute("id_pret", idPret);
        model.addAttribute("surplus_jours", surplusJours);
        model.addAttribute("listePrets", pretService.findAllWithAdherentAndExemplaireAndLivre());
        return "bibliothecaire/confirmation-prolongement";
    }

    @PostMapping("/confirmation")
    public String confirmation(
        @RequestParam("id_pret") int idPret,
        @RequestParam("surplus") int surplusJours,
        @RequestParam("daty") String date_prolongement,
        Model model
    )
    {
        Pret pret = pretService.findByIdWithAdherentAndTypeAdherent(idPret);
        // Conversion en Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateProl = null;
        try {
            dateProl = sdf.parse(date_prolongement);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Erreur de format de date");
            model.addAttribute("messageType", "error");
            return "prolonger/home";
        }

        // Save dans PROLONGEMENT
        Prolongement prolongement = new Prolongement();
        prolongement.setPret(pret);
        prolongement.setJours_supplementaires(surplusJours);
        prolongement.setDaty(dateProl);
        prolongementService.save(prolongement);

        // Update de la date retour dans PRET
        Date dateRetourPrevu = pret.getDate_retour_prevu();
        long millisRetour = dateRetourPrevu.getTime() + (surplusJours * 24L * 60 * 60 * 1000);
        Date newRetour = new Date(millisRetour);
        if (newRetour.before(dateProl))
        {
            model.addAttribute("message", "La date de l'action doit etre avant la nouvelle date de retour");
            model.addAttribute("messageType", "error");
            return "/prolonger/home";
        }
        pretService.updateDateRetourPrevuById(idPret, newRetour);
        model.addAttribute("message", "Pret prolonge avec succes");
        model.addAttribute("messageType", "success");

        return "/prolonger/home";
        
    }
    

}