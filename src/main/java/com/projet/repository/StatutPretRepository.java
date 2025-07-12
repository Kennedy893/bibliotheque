package com.projet.repository;

import com.projet.entity.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutPretRepository extends JpaRepository<StatutPret, Integer> {
    StatutPret findByPret(Pret pret);
    Optional<StatutPret> findTopByPretIdOrderByIdDesc(int pretId);
}
