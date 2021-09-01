package com.challenge.app.models.service;

import java.util.List;

import com.challenge.app.models.entity.Character;

public interface ICharacterService {

    public List<Character> getAll();

    public List<Character> getAllByName(String name);

    public List<Character> getAllByAge(Integer age);

    public List<Character> getAllByWeight(Double weight);

    public List<Character> getAllByMovie(Long idMovie);

    public Character findById(Long idCharacter);

    public Character create(Character character);

    public Character update(Character character, Long idCharacter);

    public void delete(Long idCharacter);

}
