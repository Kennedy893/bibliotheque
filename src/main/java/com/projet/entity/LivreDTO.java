package com.projet.entity;

import java.util.List;
import java.util.stream.Collectors;

public class LivreDTO 
{
    private int id;
    private String titre;
    private String auteur;
    private int anneePublication;
    private int nombreExemplaires;
    private List<String> genres;  // noms des genres
    private int exemplairesDisponibles;

    public LivreDTO(Livre livre, int exemplairesDisponibles) 
    {
        this.id = livre.getId();
        this.titre = livre.getTitre();
        this.auteur = livre.getAuteur();
        this.anneePublication = livre.getAnnee_publication();
        this.nombreExemplaires = livre.getNombre_exemplaires();

        // Charger les noms des genres (si la relation est bien initialisée)
        this.genres = livre.getGenres().stream()
                           .map(Genre::getGenre)
                           .collect(Collectors.toList());
        this.exemplairesDisponibles = exemplairesDisponibles;
    }

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

    public int getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public int getNombreExemplaires() {
        return nombreExemplaires;
    }

    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getExemplairesDisponibles() {
        return exemplairesDisponibles;
    }

    public void setExemplairesDisponibles(int exemplairesDisponibles) {
        this.exemplairesDisponibles = exemplairesDisponibles;
    }

    
}
