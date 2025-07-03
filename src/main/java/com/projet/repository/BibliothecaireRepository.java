package com.projet.repository;

import com.projet.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliothecaireRepository extends JpaRepository<Bibliothecaire, Integer> {
    Bibliothecaire findByPseudoAndMdp(String pseudo, String mdp);
}
