package com.projet.repository;

import com.projet.entity.Adherent;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
    Adherent findByNomAndMdp(String nom, String mdp);

    @Query("SELECT a FROM Adherent a LEFT JOIN FETCH a.type_adherent WHERE a.id = :id")
    Optional<Adherent> findByIdWithTypeAdherent(@Param("id") int id);

}
