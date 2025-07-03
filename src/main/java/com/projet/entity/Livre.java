package com.projet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "livre")
public class Livre
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;

    private String auteur;

    private int annee_publication;

    private int nombre_exemplaires;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Livre_Genre",
    joinColumns = @JoinColumn(name = "id_livre"), 
    inverseJoinColumns = @JoinColumn(name = "id_genre"))
    private List<Genre> genres;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getAnnee_publication() {
        return annee_publication;
    }

    public void setAnnee_publication(int annee_publication) {
        this.annee_publication = annee_publication;
    }

    public int getNombre_exemplaires() {
        return nombre_exemplaires;
    }

    public void setNombre_exemplaires(int nombre_exemplaires) {
        this.nombre_exemplaires = nombre_exemplaires;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}