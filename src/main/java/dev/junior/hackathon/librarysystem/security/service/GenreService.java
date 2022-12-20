package dev.junior.hackathon.librarysystem.security.service;

import dev.junior.hackathon.librarysystem.model.Book;
import dev.junior.hackathon.librarysystem.model.Genre;
import dev.junior.hackathon.librarysystem.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private GenreRepository genreRepository;
    public GenreService(GenreRepository genreRepository){
        this.genreRepository=genreRepository;
    }
    public List<Genre> listGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findGenreById(id).orElse(null);
    }
}
