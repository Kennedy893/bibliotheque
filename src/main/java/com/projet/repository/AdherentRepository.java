package com.projet.repository;

import com.projet.entity.Adherent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
    Adherent findByNomAndMdp(String nom, String mdp);
}
