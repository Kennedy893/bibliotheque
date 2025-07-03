package com.projet.service;

import com.projet.entity.StatutQuota;
import com.projet.repository.StatutQuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StatutQuotaService {
    @Autowired
    private StatutQuotaRepository statutQuotaRepository;

    public List<StatutQuota> findAll() {
        return statutQuotaRepository.findAll();
    }
    public Optional<StatutQuota> findById(int id) {
        return statutQuotaRepository.findById(id);
    }
    public StatutQuota save(StatutQuota statutQuota) {
        return statutQuotaRepository.save(statutQuota);
    }
    public void deleteById(int id) {
        statutQuotaRepository.deleteById(id);
    }
    public Optional<StatutQuota> findByAdherentId(int idAdherent) 
    {
        return Optional.ofNullable(statutQuotaRepository.findByAdherentId(idAdherent));
    }
}
