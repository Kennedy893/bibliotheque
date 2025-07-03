package com.projet.repository;

import com.projet.entity.StatutQuota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutQuotaRepository extends JpaRepository<StatutQuota, Integer> {
    StatutQuota findByAdherentId(int idAdherent);
}
