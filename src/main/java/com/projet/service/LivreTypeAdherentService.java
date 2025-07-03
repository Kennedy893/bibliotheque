package com.projet.service;

import com.projet.entity.Livre;
import com.projet.entity.LivreTypeAdherent;
import com.projet.entity.TypeAdherent;
import com.projet.repository.LivreTypeAdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivreTypeAdherentService {
    @Autowired
    private LivreTypeAdherentRepository livreTypeAdherentRepository;

    public List<LivreTypeAdherent> findAll() {
        return livreTypeAdherentRepository.findAll();
    }

    public Optional<LivreTypeAdherent> findById(int id) {
        return livreTypeAdherentRepository.findById(id);
    }

    public LivreTypeAdherent save(LivreTypeAdherent lta) {
        return livreTypeAdherentRepository.save(lta);
    }

    public void deleteById(int id) {
        livreTypeAdherentRepository.deleteById(id);
    }

    public Optional<LivreTypeAdherent> findByLivreAndTypeAdherent(Livre livre, TypeAdherent typeAdherent) 
    {
        return Optional.ofNullable(livreTypeAdherentRepository.findByLivreAndTypeAdherent(livre, typeAdherent));
        // return Optional.ofNullable(livreTypeAdherentRepository.findByLivreAndTypeAdherent(idLivre, idTypeAdherent));
        
    }
}
