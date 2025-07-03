package com.projet.repository;

import com.projet.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByPseudoAndMdp(String pseudo, String mdp);
}
