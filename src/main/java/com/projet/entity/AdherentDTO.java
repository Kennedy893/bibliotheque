package com.projet.entity;

import java.util.List;
import java.util.stream.Collectors;

public class AdherentDTO 
{
    private int id;
    private String nom;
    private String email;
    private String typeAdherent;
    private int nbQuotaMax;
    private int nbQuota;
    private boolean abonnement;
    private boolean penalisation;

    public AdherentDTO(Adherent adherent, int nbQuota) 
    {
        this.id = adherent.getId();
        this.nom = adherent.getNom();
        this.email = adherent.getEmail();
        this.typeAdherent = adherent.getType_adherent() != null 
            ? adherent.getType_adherent().getType_adherent() 
            : null;
        this.nbQuotaMax = adherent.getType_adherent().getQuota();
        this.nbQuota = nbQuota;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getTypeAdherent() {
        return typeAdherent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTypeAdherent(String typeAdherent) {
        this.typeAdherent = typeAdherent;
    }

    public int getNbQuotaMax() {
        return nbQuotaMax;
    }

    public void setNbQuotaMax(int nbQuotaMax) {
        this.nbQuotaMax = nbQuotaMax;
    }

    public int getNbQuota() {
        return nbQuota;
    }

    public void setNbQuota(int nbQuota) {
        this.nbQuota = nbQuota;
    }

    public boolean getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(boolean abonnement) {
        this.abonnement = abonnement;
    }

    public boolean getPenalisation() {
        return penalisation;
    }

    public void setPenalisation(boolean penalisation) {
        this.penalisation = penalisation;
    }
}
