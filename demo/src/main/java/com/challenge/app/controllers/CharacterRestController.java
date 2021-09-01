package com.challenge.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.challenge.app.models.entity.Character;
import com.challenge.app.models.service.impl.CharacterServiceImpl;
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
@RequestMapping("/characters")
public class CharacterRestController {

    Map<String, Object> response = new HashMap<>();

    @Autowired
    CharacterServiceImpl characterService;

    /**
     * 
     * @param idMovie
     * @return
     */
    @ApiOperation(value = "")
    @GetMapping("/{idCharacter}")
    public ResponseEntity<?> findById(@PathVariable Long idCharacter) {
        Character character = null;
        try {
            character = characterService.findById(idCharacter);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error when querying the database.");
        }
        return new ResponseEntity<Character>(character, HttpStatus.OK);
    }

    /**
     * 
     * @param movie
     * @return
     */
    @ApiOperation(value = "")
    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody Character character) {
        Character characterN = null;
        try {
            characterN = characterService.create(character);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error when querying the database.");
        }
        response.put("message", "SUCCESS -- Character was created. Title: ".concat(characterN.getName()));
        response.put("movie", characterN);
        return new ResponseEntity<Character>(characterN, HttpStatus.OK);
    }

    /**
     * 
     * @param m
     * @param idMovie
     * @return
     */
    @ApiOperation(value = "")
    @PutMapping("/{idMovie}")
    public ResponseEntity<?> update(@Valid @RequestBody Character c, @PathVariable Long idCharacter) {
        Character characterN = null;
        try {
            characterN = characterService.update(c, idCharacter);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error when querying the database.");
        }
        response.put("message", "SUCCESS -- Character was created. Title: ".concat(characterN.getName()));
        response.put("movie", characterN);
        return new ResponseEntity<Character>(characterN, HttpStatus.OK);
    }

    /**
     * 
     * @param idMovie
     * @return
     */
    @ApiOperation(value = "")
    @DeleteMapping("/{idMovie}")
    public ResponseEntity<?> delete(@PathVariable Long idCharacter) {
        Character character = characterService.findById((idCharacter));
        try {
            characterService.delete(idCharacter);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error when querying the database.");
        }
        response.put("message", "SUCCESS -- Character was deleted. Title: ".concat(character.getName()));
        response.put("movie", character);
        return new ResponseEntity<Character>(character, HttpStatus.OK);
    }

    /**
     * LISTS
     * 
     */

    /**
     * 
     * @return
     */
    @ApiOperation(value = "")
    @GetMapping()
    @JsonView(Views.Public.class)
    public ResponseEntity<?> getAll() {
        List<Character> characters = null;
        try {
            characters = characterService.getAll();
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error when querying the database.");
        }
        return new ResponseEntity<List<Character>>(characters, HttpStatus.OK);
    }

    // /characters?name=X
    /**
     * 
     * @param name
     * @return
     */
    @ApiOperation(value = "")
    @JsonView(Views.Public.class)
    @RequestMapping(value = "", params = "name", method = RequestMethod.GET)
    public List<Character> getAllByNombre(@RequestParam(name = "name") String name) {
        List<Character> characters = characterService.getAllByName(name);
        return characters;
    }

    // /characters?age=X
    /**
     * 
     * @param edad
     * @return
     */
    @ApiOperation(value = "")
    @JsonView(Views.Public.class)
    @RequestMapping(value = "", params = "age", method = RequestMethod.GET)
    public List<Character> getAllByEdad(@RequestParam(name = "age") Integer edad) {
        List<Character> characters = characterService.getAllByAge(edad);
        return characters;
    }

    // /characters?idMovie=X
    /**
     * 
     * @param idMovie
     * @return
     */
    @ApiOperation(value = "")
    @JsonView(Views.Public.class)
    @RequestMapping(value = "", params = "idMovie", method = RequestMethod.GET)
    public List<Character> getAllByPelicula(@RequestParam(name = "idMovie") Long idMovie) {
        List<Character> characters = characterService.getAllByMovie(idMovie);
        return characters;
    }

}
