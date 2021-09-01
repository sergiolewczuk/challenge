package com.challenge.app.models.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.challenge.app.models.entity.Character;
import com.challenge.app.models.exceptions.EmptyListException;
import com.challenge.app.models.repository.ICharacterRepository;
import com.challenge.app.models.service.ICharacterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacterServiceImpl implements ICharacterService {

    @Autowired
    ICharacterRepository characterRepository;

    @Override
    @Transactional(readOnly = true)
    public Character findById(Long idCharacter) {
        return characterRepository.findById(idCharacter).orElseThrow(() -> new NoSuchElementException("Character ID was not found. By ID: " + idCharacter));
    }

    @Override
    @Transactional
    public Character create(Character ch) {
        return characterRepository.save(ch);
    }

    @Override
    @Transactional
    public Character update(Character character, Long idCharacter) {
        Optional<Character> characterOld = Optional.of(characterRepository.getById(idCharacter));
        Character characterNew = null;

        if (characterOld.isPresent()) {
            characterNew = characterOld.get();
            characterNew.setName(character.getName());
            characterNew.setImage(character.getImage());
            characterNew.setAge(character.getAge());
            characterNew.setHistory(character.getHistory());
            characterNew.setMovies(character.getMovies());
            characterNew.setWeight(character.getWeight());
            characterNew = create(characterNew);
            return characterNew;
        } else {
            throw new NoSuchElementException("Character ID was not found. By ID: " + idCharacter);
        }
    }

    @Override
    @Transactional
    public void delete(Long idCharacter) {
        Optional<Character> characterOld = Optional.of(characterRepository.getById(idCharacter));
        Character character = null;

        if (characterOld.isPresent()) {
            character = characterOld.get();
            character.removeMovies();
            characterRepository.delete(character);
        } else {
            throw new NoSuchElementException("Character ID was not found. By ID: " + idCharacter);
        }
    }

    /**
     * LISTS
     */

    @Override
    @Transactional(readOnly = true)
    public List<Character> getAll() {
        List<Character> list = characterRepository.findAll();
        if (list.isEmpty()) {
            throw new EmptyListException("Characters empty List.");
        } else {
            return list;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Character> getAllByName(String name) {
        List<Character> list = characterRepository.getAllByName(name);
        if (list.isEmpty()) {
            throw new EmptyListException("Characters empty List.");
        } else {
            return list;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Character> getAllByAge(Integer age) {
        List<Character> list = characterRepository.getAllByAge(age);
        if (list.isEmpty()) {
            throw new EmptyListException("Characters empty List.");
        } else {
            return list;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Character> getAllByWeight(Double weight) {
        List<Character> list = characterRepository.getAllByWeight(weight);
        if (list.isEmpty()) {
            throw new EmptyListException("Characters empty List.");
        } else {
            return list;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Character> getAllByMovie(Long idMovie) {
        List<Character> list = characterRepository.getAllByMovie(idMovie);
        if (list.isEmpty()) {
            throw new EmptyListException("Characters empty List.");
        } else {
            return list;
        }
    }

}
