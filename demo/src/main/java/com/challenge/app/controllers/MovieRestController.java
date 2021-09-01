package com.challenge.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.challenge.app.models.entity.Movie;
import com.challenge.app.models.service.impl.MovieServiceImpl;
import com.challenge.app.models.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/movies")
public class MovieRestController {

    Map<String, Object> response = new HashMap<>();

    @Autowired
    MovieServiceImpl movieService;

    /**
     * 
     * @param idMovie
     * @return
     */
    @ApiOperation(value =  "")
    @GetMapping("/{idMovie}")
    public ResponseEntity<?> findById(@PathVariable Long idMovie) {
        Movie movie = null;
        try {
            movie = movieService.findById(idMovie);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error when querying the database.");
        }
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }

    /**
     * 
     * @param movie
     * @return
     */
    @ApiOperation(value = "")
    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody Movie movie) {
        Movie movieN = null;
        try {
            movieN = movieService.create(movie);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error when querying the database.");
        }
        response.put("message", "SUCCESS -- Movie was created. Title: ".concat(movieN.getTitle()));
        response.put("movie", movieN);
        return new ResponseEntity<Movie>(movieN, HttpStatus.OK);
    }

    /**
     * 
     * @param m
     * @param idMovie
     * @return
     */
    @ApiOperation(value = "")
    @PutMapping("/{idMovie}")
    public ResponseEntity<?> update(@Valid @RequestBody Movie m, @PathVariable Long idMovie) {

        Movie movieN = null;

        try {
            movieN = movieService.update(m, idMovie);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error when querying the database.");
        }

        response.put("message", "SUCCESS -- Movie was updated. Title: ".concat(movieN.getTitle()));
        response.put("movie", movieN);

        return new ResponseEntity<Movie>(movieN, HttpStatus.OK);
    }

    /**
     * 
     * @param idMovie
     * @return
     */
    @DeleteMapping("/{idMovie}")
    public ResponseEntity<?> delete(@PathVariable Long idMovie) {

        Movie movie = movieService.findById((idMovie));

        try {
            movieService.delete(idMovie);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error when querying the database.");
        }

        response.put("message", "SUCCESS -- Movie was deleted. Title: ".concat(movie.getTitle()));
        response.put("movie", movie);

        return new ResponseEntity<Movie>(movie, HttpStatus.OK);

    }

    /**
     * LISTS
     * 
     */

    /**
     * 
     * @return
     */
    @GetMapping()
    @JsonView(Views.Public.class)
    public ResponseEntity<?> getAll() {
        List<Movie> movies = null;
        try {
            movies = movieService.getAll();
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error when querying the database.");
        }
        return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
    }

    // /movies?name=X
    /**
     * 
     * @param title
     * @return
     */
    @JsonView(Views.Public.class)
    @RequestMapping(value = "", params = "name", method = RequestMethod.GET)
    public List<Movie> findAllByTitle(@RequestParam(name = "name") String title) {
        List<Movie> movies = movieService.findAllByTitle(title);
        return movies;
    }

    // /movies?genre={genre}
    /**
     * 
     * @param idGenre
     * @return
     */
    @JsonView(Views.Public.class)
    @RequestMapping(value = "", params = "genre", method = RequestMethod.GET)
    public List<Movie> findAllByGenre(@RequestParam(name = "genre") Long idGenre) {
        List<Movie> movies = movieService.findAllByGenre(idGenre);
        return movies;
    }

    // /movies?order=X
    /**
     * 
     * @param order
     * @return
     */
    @JsonView(Views.Public.class)
    @RequestMapping(value = "", params = "order", method = RequestMethod.GET)
    public List<Movie> findAllOrderByCreationDate(@RequestParam(name = "order") String order) {
        List<Movie> movies = movieService.findAllOrderCreationDate(order);
        return movies;
    }

}
