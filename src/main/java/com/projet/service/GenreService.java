package com.projet.service;

import com.projet.entity.Genre;
import com.projet.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
    public Optional<Genre> findById(int id) {
        return genreRepository.findById(id);
    }
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }
    public void deleteById(int id) {
        genreRepository.deleteById(id);
    }
}
