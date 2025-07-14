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
    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private LivreService livreService;

    @GetMapping("/home")
    public String home(Model model) 
    {
        model.addAttribute("listePrets", pretService.findAllWithAdherentAndExemplaireAndLivre());
        model.addAttribute("refus", "Pret refuse");
        return "prolonger/home";
    }

    @PostMapping("/stockParams")
    public String stockParams(
        @RequestParam("id_pret") int idPret,
        @RequestParam("surplus_jours") int surplusJours,
        Model model,
        jakarta.servlet.http.HttpSession session
    )
    {
        // model.addAttribute("id_pret", idPret);
        // model.addAttribute("surplus_jours", surplusJours);
        session.setAttribute("id_pret", idPret);
        session.setAttribute("surplus_jours", surplusJours);
        model.addAttribute("listePrets", pretService.findAllWithAdherentAndExemplaireAndLivre());
        return "login-bibliothecaire";
    }

    @GetMapping("/confirmation")
    public String confirmation(
        // @RequestParam("id_pret") int idPret,
        // @RequestParam("surplus") int surplusJours,
        @RequestParam("daty") String date_prolongement,
        Model model,
        jakarta.servlet.http.HttpSession session
    )
    {
        Integer idPret = (Integer) session.getAttribute("id_pret");
        Integer surplusJours = (Integer) session.getAttribute("surplus_jours");
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

        // REGLES DE GESTION
        // Verfication si l'adherent est actif pendant la duree
        Date dateRetourPrevu2 = pret.getDate_retour_prevu();
        long millisRetour2 = dateRetourPrevu2.getTime() + (surplusJours * 24L * 60 * 60 * 1000);
        Date newRetour2 = new Date(millisRetour2);
        Inscription inscription = inscriptionService.findByAdherentId(pret.getAdherent().getId());
        Date exp = inscription.getDate_expiration();
        if (exp.before(newRetour2)) 
        {
            model.addAttribute("message", "L'adherent n'est plus actif pendant cette date");
            model.addAttribute("messageType", "error");
            return "prolonger/home";
        }

        // Verif s'il y avait deja un prolongement sur ce pret
        Prolongement p = prolongementService.findByPretId(idPret);
        if (p != null) 
        {
            model.addAttribute("message", "Il y avait deja un prolongement sur ce pret");
            model.addAttribute("messageType", "error");
            return "prolonger/home";
        }

        // Verif quota
        StatutQuota statutQuota = statutQuotaService.findTopByAdherentIdOrderByIdDesc(pret.getAdherent().getId()).orElse(null);
        int nbQuota = statutQuota.getQuota();
        if (nbQuota <= 0) 
        {
            model.addAttribute("message", "Le nombre de quota de l'adehrent est epuise");
            model.addAttribute("messageType", "error");
            return "prolonger/home";
        }

        // Verif nb exemplaire
        Exemplaire exe = exemplaireService.findById(pret.getExemplaire().getId()).orElse(null);
        Livre livre = exe.getLivre();
        Exemplaire exemp = exemplaireService.findTopByLivreIdOrderByIdDesc(livre.getId()).orElse(null);
        if (exemp.getNb_exemplaires() <= 0) 
        {
            model.addAttribute("message", "Il n'y a plus d'exemplaire disponible");
            model.addAttribute("messageType", "error");
            return "prolonger/home";
        }

        // //////////////

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

        // Update du statut du pret
        StatutPret sp = new StatutPret();
        sp.setDaty(dateProl);
        sp.setStatut(2); // 2: Prolongé
        sp.setPret(pret);
        statutPretService.save(sp);

        // Update du statut de quota
        StatutQuota sq = new StatutQuota();
        sq.setDaty(dateProl);
        sq.setQuota(nbQuota-1);
        sq.setAdherent(pret.getAdherent());
        statutQuotaService.save(sq);

        // Update nb exemplaire
        Exemplaire e = new Exemplaire();
        e.setDaty(dateProl);
        e.setLivre(livre); 
        e.setNb_exemplaires(exemp.getNb_exemplaires() - 1);
        exemplaireService.save(e);

        model.addAttribute("message", "Pret prolonge avec succes");
        model.addAttribute("messageType", "success");

        return "/prolonger/home";
        
    }
    

}