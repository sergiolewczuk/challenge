package com.challenge.app.models.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.challenge.app.models.entity.Movie;
import com.challenge.app.models.exceptions.EmptyListException;
import com.challenge.app.models.repository.IMovieRepository;
import com.challenge.app.models.service.IMovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieServiceImpl implements IMovieService {

    @Autowired
    IMovieRepository movieRepository;

    @Override
    @Transactional(readOnly = true)
    public Movie findById(Long idMovie) {
        return movieRepository.findById(idMovie).orElseThrow(() -> new NoSuchElementException("Movie ID was not found. By ID: " + idMovie));
    }

    @Override
    @Transactional
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    @Transactional
    public Movie update(Movie movie, Long idMovie) {

        Optional<Movie> movieOld = Optional.of(movieRepository.getById(idMovie));
        Movie movieNew = null;

        if (movieOld.isPresent()) {
            movieNew = movieOld.get();

            movieNew.setTitle(movie.getTitle());
            movieNew.setImage(movie.getImage());
            movieNew.setScore(movie.getScore());
            movieNew.setCharacters(movie.getCharacters());
            movieNew.setGenre(movie.getGenre());
            movieNew.setCreateAt(movie.getCreateAt());

            movieNew = create(movieNew);

            return movieNew;
        } else {
            throw new NoSuchElementException("Movie ID was not found. By ID: " + idMovie);            
        }
    }

    @Override
    @Transactional
    public void delete(Long idMovie) {

        Optional<Movie> movieOld = Optional.of(movieRepository.getById(idMovie));
        Movie movie = null;

        if (movieOld.isPresent()) {
            movie = movieOld.get();
            movieRepository.delete(movie);
        } else {
            throw new NoSuchElementException("Movie ID was not found. By ID: " + idMovie);
        }
    }

    /**
     * LISTS
     */

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAll() {
        List<Movie> list = movieRepository.findAll();

        if (list.isEmpty()) {
            throw new EmptyListException("Movies empty List.");
        } else {
            return list;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAllByTitle(String title) {
        List<Movie> list = movieRepository.getAllByTitle(title);

        if (list.isEmpty()) {
            throw new EmptyListException("Movies empty List.");
        } else {
            return list;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAllByGenre(Long idGenre) {
        List<Movie> list = movieRepository.getAllByGenre(idGenre);

        if (list.isEmpty()) {
            throw new EmptyListException("Movies empty List.");
        } else {
            return list;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAllOrderCreationDate(String orderBy) {

        List<Movie> list = null;

        if (orderBy.equalsIgnoreCase("ASC")) {
            list = movieRepository.getAllOrderByAsc();
        } else if (orderBy.equalsIgnoreCase("DESC")) {
            list = movieRepository.getAllOrderByDesc();
        }

        if (list.isEmpty()) {
            throw new EmptyListException("Movies empty List.");
        } else {
            return list;
        }
    }

}
