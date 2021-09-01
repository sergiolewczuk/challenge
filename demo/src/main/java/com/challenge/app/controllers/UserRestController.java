package com.challenge.app.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.challenge.app.models.entity.AuthenticationRequest;
import com.challenge.app.models.entity.User;
import com.challenge.app.models.exceptions.UserNotValid;
import com.challenge.app.models.service.IUserService;
import com.challenge.app.security.JWTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
public class UserRestController {
    
    @Autowired
	private IUserService userservice;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	Map<String,Object> response = new HashMap<>();

	@ApiOperation(value = "Register new User.")
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) throws IOException {		
		userservice.create(user);
		userservice.sendEmail(user.getEmail());

		response.put("message", "SUCCESS -- User was created.");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Login with Username and Password.")
	@PostMapping("/login")
	public ResponseEntity<?> generatetoken(@RequestBody AuthenticationRequest authrequest) throws Exception {		
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));
		} catch (Exception ex) {
			throw new UserNotValid();
		}
		
		response.put("token", jwtService.generateToken(authrequest.getUsername()));
		response.put("message", "Authorizated");
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
