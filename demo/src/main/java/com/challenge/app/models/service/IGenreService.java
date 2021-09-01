package com.challenge.app.models.service;

import java.util.List;

import com.challenge.app.models.entity.Genre;

public interface IGenreService {
 
    public List<Genre> findAll();    

    public Genre findById(Long idGenre);

    public Genre create(Genre genre);

    public Genre update(Genre genre, Long idGenre);

    public void delete(Long idGenre);

}
