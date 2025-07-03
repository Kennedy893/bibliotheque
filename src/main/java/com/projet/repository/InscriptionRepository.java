package com.projet.repository;

import com.projet.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {
    Inscription findByAdherentId(int idAdherent);
}
