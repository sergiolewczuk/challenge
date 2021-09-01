package com.challenge.app.models.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.challenge.app.models.entity.Genre;
import com.challenge.app.models.exceptions.EmptyListException;
import com.challenge.app.models.repository.IGenreRepository;
import com.challenge.app.models.service.IGenreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements IGenreService {

    @Autowired
    IGenreRepository genreRepository;

    @Override
    public Genre findById(Long idGenre) {
        return genreRepository.findById(idGenre).orElseThrow(() -> new NoSuchElementException("Genre ID was not found. By ID: " + idGenre));
    }

    @Override
    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void delete(Long idGenre) {

        Optional<Genre> genreOld = Optional.of(genreRepository.getById(idGenre));
        Genre genre = null;

        if (genreOld.isPresent()) {
            genre = genreOld.get();
            genreRepository.delete(genre);
        } else {
            throw new NoSuchElementException("Genre ID was not found. By ID: " + idGenre);
        }
    }

    @Override
    public Genre update(Genre genre, Long idGenre) {

        Optional<Genre> genreOld = Optional.of(genreRepository.getById(idGenre));

        Genre genreNew = null;

        if (genreOld.isPresent()) {

            genreNew = genreOld.get();

            genreNew.setName(genre.getName());
            genreNew.setImage(genre.getImage());
            genreNew.setMovies(genre.getMovies());

            genreNew = create(genreNew);

            return genreNew;
        } else {
            throw new NoSuchElementException("Genre ID was not found. By ID: " + idGenre);
        }

    }

    /**
     * LISTS
     */

    @Override
    public List<Genre> findAll() {

        List<Genre> list = genreRepository.findAll();

        if (list.isEmpty()) {
            throw new EmptyListException("Genres empty List.");
        } else {
            return list;
        }

    }

}
