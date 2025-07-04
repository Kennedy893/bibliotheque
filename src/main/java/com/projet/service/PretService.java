package com.projet.service;

import com.projet.entity.Pret;
import com.projet.repository.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PretService {
    @Autowired
    private PretRepository pretRepository;

    public List<Pret> findAll() {
        return pretRepository.findAll();
    }
    public Optional<Pret> findById(int id) {
        return pretRepository.findById(id);
    }
    public Pret save(Pret pret) {
        return pretRepository.save(pret);
    }
    public void deleteById(int id) {
        pretRepository.deleteById(id);
    }

    public List<Pret> findAllWithAdherentAndExemplaireAndLivre() 
    {
        return pretRepository.findAllWithAdherentAndExemplaireAndLivre();
    }
    public Pret findByIdWithAdherentAndTypeAdherent(int id) 
    {
        return pretRepository.findByIdWithAdherentAndTypeAdherent(id);
    }

    public Pret updateDateRetourPrevuById(int id, Date newDateRetourPrevu) 
    {
        Optional<Pret> optionalPret = pretRepository.findById(id);
        if (optionalPret.isPresent()) 
        {
            Pret pret = optionalPret.get();
            pret.setDate_retour_prevu(newDateRetourPrevu);
            return pretRepository.save(pret);
        } else {
            throw new RuntimeException("Pret not found with id: " + id);
        }
    }

    public List<Pret> filtrerPrets(String mot) 
    {
        // Exemple simple : filtrer par nom d’adhérent ou titre du livre
        return pretRepository.searchWithAdherentAndExemplaireAndLivre(mot);
    }
}
