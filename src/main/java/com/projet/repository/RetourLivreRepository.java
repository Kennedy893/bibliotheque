package com.projet.repository;

import com.projet.entity.Pret;
import com.projet.entity.RetourLivre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetourLivreRepository extends JpaRepository<RetourLivre, Integer> {
    public RetourLivre findByPret(Pret pret);
}
