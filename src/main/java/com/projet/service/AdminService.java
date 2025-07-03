package com.projet.service;

import com.projet.entity.Admin;
import com.projet.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }
    public Optional<Admin> findById(int id) {
        return adminRepository.findById(id);
    }
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }
    public void deleteById(int id) {
        adminRepository.deleteById(id);
    }
    public Admin findByPseudoAndMdp(String pseudo, String mdp) {
        return adminRepository.findByPseudoAndMdp(pseudo, mdp);
    }
}
