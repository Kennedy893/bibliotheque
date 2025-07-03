package com.projet.service;

import com.projet.entity.Inscription;
import com.projet.repository.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {
    @Autowired
    private InscriptionRepository inscriptionRepository;

    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }
    public Optional<Inscription> findById(int id) {
        return inscriptionRepository.findById(id);
    }
    public Inscription save(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }
    public void deleteById(int id) {
        inscriptionRepository.deleteById(id);
    }
    
    public Inscription findByAdherentId(int idAdherent) 
    {
        return inscriptionRepository.findByAdherentId(idAdherent);
    }
}
