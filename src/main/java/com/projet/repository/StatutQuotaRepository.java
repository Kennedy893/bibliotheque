package com.projet.repository;

import com.projet.entity.Exemplaire;
import com.projet.entity.StatutQuota;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatutQuotaRepository extends JpaRepository<StatutQuota, Integer> {
    StatutQuota findByAdherentId(int idAdherent);

    Optional<StatutQuota> findTopByAdherentIdOrderByIdDesc(int adherentId);

    @Query("""
        SELECT sq FROM StatutQuota sq
        JOIN FETCH sq.adherent
        WHERE sq.id IN (
            SELECT MAX(sq2.id) FROM StatutQuota sq2 GROUP BY sq2.adherent.id
        )
    """)
    List<StatutQuota> findAllWithAdherent();

}
