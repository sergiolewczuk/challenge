package com.challenge.app.models.service;

import java.util.List;

import com.challenge.app.models.entity.Movie;

public interface IMovieService {

    public List<Movie> findAllByTitle(String title);

    public List<Movie> findAllByGenre(Long idGenre);

    public List<Movie> findAllOrderCreationDate(String orderBy);


    public List<Movie> getAll();

    public Movie findById(Long idMovie);

    public Movie create(Movie movie);

    public Movie update(Movie update, Long idMovie);

    public void delete(Long idMovie);
}
