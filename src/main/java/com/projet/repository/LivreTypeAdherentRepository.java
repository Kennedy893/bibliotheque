package com.projet.repository;

import com.projet.entity.Livre;
import com.projet.entity.LivreTypeAdherent;
import com.projet.entity.TypeAdherent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreTypeAdherentRepository extends JpaRepository<LivreTypeAdherent, Integer> {
    // LivreTypeAdherent findByLivreAndType_adherent(int idLivre, int idTypeAdherent);
    LivreTypeAdherent findByLivreAndTypeAdherent(Livre livre, TypeAdherent typeAdherent);
}
