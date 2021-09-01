package com.challenge.app.models.service;

import java.io.IOException;

import com.challenge.app.models.entity.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService {
    
    public User findByUsername(String username);

    public void create(User user);

    public void sendEmail(String email) throws IOException;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    

}
