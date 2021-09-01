package com.challenge.app.models.repository;

import java.util.List;

import com.challenge.app.models.entity.Character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICharacterRepository extends JpaRepository<Character, Long> {
    
    @Query(value = "SELECT c FROM Character c WHERE c.name=:name")
    public List<Character> getAllByName(@Param("name") String name);

    @Query(value = "SELECT c FROM Character c WHERE c.age=:age")
    public List<Character> getAllByAge(@Param("age") Integer age);

    @Query(value = "SELECT c FROM Character c WHERE c.weight=:weight")
    public List<Character> getAllByWeight(@Param("weight") Double weight);
    
    @Query(value = "SELECT c FROM Character c INNER JOIN c.movies cm WHERE cm.idMovie=:idMovie")
    public List<Character> getAllByMovie(@Param("idMovie") Long idMovie);
    


}
