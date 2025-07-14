package com.projet.repository;

import com.projet.entity.Exemplaire;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
    

    @Query("""
    SELECT e FROM Exemplaire e JOIN FETCH e.livre
    WHERE e.id IN (
        SELECT MAX(e2.id) FROM Exemplaire e2 GROUP BY e2.livre.id
    )
    """)
    List<Exemplaire> findAllWithLivre();

    Optional<Exemplaire> findTopByLivreIdOrderByIdDesc(int livreId);
}
