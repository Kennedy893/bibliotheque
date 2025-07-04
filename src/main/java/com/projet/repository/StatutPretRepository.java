package com.projet.repository;

import com.projet.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutPretRepository extends JpaRepository<StatutPret, Integer> {
    StatutPret findByPret(Pret pret);
}
