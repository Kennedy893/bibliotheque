package com.projet.repository;

import com.projet.entity.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivreRepository extends JpaRepository<Livre, Integer> 
{
    // Livre findByExemplaireId(int idExemplaire);

    @Query("SELECT l FROM Livre l LEFT JOIN FETCH l.genres WHERE l.id = :id")
    Optional<Livre> findByIdWithGenres(@Param("id") int id);

}
