package com.projet.service;

import com.projet.entity.RetourLivre;
import com.projet.repository.RetourLivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RetourLivreService {
    @Autowired
    private RetourLivreRepository retourLivreRepository;

    public List<RetourLivre> findAll() {
        return retourLivreRepository.findAll();
    }
    public Optional<RetourLivre> findById(int id) {
        return retourLivreRepository.findById(id);
    }
    public RetourLivre save(RetourLivre retourLivre) {
        return retourLivreRepository.save(retourLivre);
    }
    public void deleteById(int id) {
        retourLivreRepository.deleteById(id);
    }
}
