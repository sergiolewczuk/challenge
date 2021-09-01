package com.challenge.app.models.repository;

import java.util.List;

import com.challenge.app.models.entity.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {
    
    @Query(value = "SELECT m FROM Movie m WHERE m.title=:title")
    public List<Movie> getAllByTitle(@Param("title") String title);

    @Query(value = "SELECT m FROM Movie m WHERE id_genre=:idGenre")
    public List<Movie> getAllByGenre(@Param("idGenre") Long idGenre);

    @Query(value = "SELECT m FROM Movie m ORDER BY create_at ASC")
    public List<Movie> getAllOrderByAsc();

    @Query(value = "SELECT m FROM Movie m ORDER BY create_at DESC")
    public List<Movie> getAllOrderByDesc();

}
