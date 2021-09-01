package com.challenge.app.models.repository;

import com.challenge.app.models.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    
    @Query(value = "SELECT u FROM User u WHERE u.username=:username")
    public User findByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE u.username=:username OR u.email=:email")
    public User findByUsernameOrEmail(String username, String email);

}
