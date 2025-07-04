package com.projet.repository;

import com.projet.entity.Pret;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PretRepository extends JpaRepository<Pret, Integer> {
    @Query("SELECT p FROM Pret p JOIN FETCH p.adherent JOIN FETCH p.exemplaire e JOIN FETCH e.livre")
    List<Pret> findAllWithAdherentAndExemplaireAndLivre();

    @Query("SELECT p FROM Pret p JOIN FETCH p.adherent a JOIN FETCH a.type_adherent WHERE p.id = :id")
    Pret findByIdWithAdherentAndTypeAdherent(@Param("id") int id);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.data.jpa.repository.Query("UPDATE Pret p SET p.date_retour_prevu = :dateRetourPrevu WHERE p.id = :id")
    void updateDateRetourPrevuById(@org.springframework.data.repository.query.Param("id") int id, @org.springframework.data.repository.query.Param("dateRetourPrevu") java.util.Date dateRetourPrevu);

    // List<Pret> findByAdherentNomContainingIgnoreCaseOrExemplaireLivreTitreContainingIgnoreCase(String nom, String titre);
    @Query("SELECT p FROM Pret p JOIN FETCH p.adherent a JOIN FETCH p.exemplaire e JOIN FETCH e.livre l WHERE LOWER(a.nom) LIKE LOWER(CONCAT('%', :mot, '%')) OR LOWER(l.titre) LIKE LOWER(CONCAT('%', :mot, '%'))")
    List<Pret> searchWithAdherentAndExemplaireAndLivre(@org.springframework.data.repository.query.Param("mot") String mot);
}
