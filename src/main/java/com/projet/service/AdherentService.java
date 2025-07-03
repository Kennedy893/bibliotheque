package com.projet.service;

import com.projet.entity.Adherent;
import com.projet.repository.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdherentService {
    @Autowired
    private AdherentRepository adherentRepository;

    public List<Adherent> findAll() {
        return adherentRepository.findAll();
    }
    public Optional<Adherent> findById(int id) {
        return adherentRepository.findById(id);
    }
    public Adherent save(Adherent adherent) {
        return adherentRepository.save(adherent);
    }
    public void deleteById(int id) {
        adherentRepository.deleteById(id);
    }
    public Adherent findByNomAndMdp(String nom, String mdp) {
        return adherentRepository.findByNomAndMdp(nom, mdp);
    }
}
