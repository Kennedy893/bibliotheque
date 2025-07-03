package com.projet.repository;

import com.projet.entity.Pret;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PretRepository extends JpaRepository<Pret, Integer> {
    // @Query("SELECT p FROM Pret p JOIN FETCH p.adherent JOIN FETCH p.exemplaire e JOIN FETCH e.livre")
    // List<Pret> findAllWithAdherentAndExemplaireAndLivre();

    @Query("SELECT p FROM Pret p JOIN FETCH p.adherent JOIN FETCH p.exemplaire e JOIN FETCH e.livre JOIN p.statutPret s WHERE s.statut = 0")
    List<Pret> findAllWithAdherentAndExemplaireAndLivre();
}
