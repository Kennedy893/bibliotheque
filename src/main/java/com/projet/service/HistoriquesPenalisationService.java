package com.projet.service;

import com.projet.entity.HistoriquesPenalisation;
import com.projet.repository.HistoriquesPenalisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HistoriquesPenalisationService {
    @Autowired
    private HistoriquesPenalisationRepository historiquesPenalisationRepository;

    public List<HistoriquesPenalisation> findAll() {
        return historiquesPenalisationRepository.findAll();
    }
    public Optional<HistoriquesPenalisation> findById(int id) {
        return historiquesPenalisationRepository.findById(id);
    }
    public HistoriquesPenalisation save(HistoriquesPenalisation h) {
        return historiquesPenalisationRepository.save(h);
    }
    public void deleteById(int id) {
        historiquesPenalisationRepository.deleteById(id);
    }

    public Optional<HistoriquesPenalisation> findByAdherentId(int idAdherent) 
    {
        return Optional.ofNullable(historiquesPenalisationRepository.findByAdherentId(idAdherent));
    }
}
