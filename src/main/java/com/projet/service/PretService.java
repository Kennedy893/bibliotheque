package com.projet.service;

import com.projet.entity.Pret;
import com.projet.repository.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
