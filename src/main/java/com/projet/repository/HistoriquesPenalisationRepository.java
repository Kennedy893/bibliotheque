package com.projet.repository;

import com.projet.entity.HistoriquesPenalisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriquesPenalisationRepository extends JpaRepository<HistoriquesPenalisation, Integer> {
    HistoriquesPenalisation findByAdherentId(int idAdherent);
}
