package com.projet.service;

import com.projet.entity.StatutPret;
import com.projet.repository.StatutPretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StatutPretService {
    @Autowired
    private StatutPretRepository statutPretRepository;

    public List<StatutPret> findAll() {
        return statutPretRepository.findAll();
    }
    public Optional<StatutPret> findById(int id) {
        return statutPretRepository.findById(id);
    }
    public StatutPret save(StatutPret statutPret) {
        return statutPretRepository.save(statutPret);
    }
    public void deleteById(int id) {
        statutPretRepository.deleteById(id);
    }
}
