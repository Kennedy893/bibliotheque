package com.projet.service;

import com.projet.entity.Prolongement;
import com.projet.repository.ProlongementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProlongementService {
    @Autowired
    private ProlongementRepository prolongementRepository;

    public List<Prolongement> findAll() {
        return prolongementRepository.findAll();
    }
    public Optional<Prolongement> findById(int id) {
        return prolongementRepository.findById(id);
    }
    public Prolongement save(Prolongement prolongement) {
        return prolongementRepository.save(prolongement);
    }
    public void deleteById(int id) {
        prolongementRepository.deleteById(id);
    }
}
