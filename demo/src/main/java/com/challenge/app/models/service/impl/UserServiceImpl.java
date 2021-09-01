package com.challenge.app.models.service.impl;

import java.io.IOException;
import java.util.ArrayList;

import com.challenge.app.models.entity.User;
import com.challenge.app.models.repository.IUserRepository;
import com.challenge.app.models.service.IUserService;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Type you sendgrid key here
	private final String KEY = "INSERT YOU KEY HERE";

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void create(User user) {

        User userExample = new User(user.getUsername(), user.getEmail());
        if (userRepository.exists(Example.of(userExample))) {
            throw new DuplicateKeyException("Duplicated entry. Username or Email exist.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    public void sendEmail(String email) throws IOException {

        Email from = new Email("challenge.example@outlook.com.ar");
		String subject = "Welcome";
		Email to = new Email(email);
		Content content = new Content("text/plain", "Welcome to Api Disney");
		Mail mail = new Mail(from, subject, to, content);		
		SendGrid sg = new SendGrid(KEY);
		Request request = new Request();

		try {

			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);


			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());


		} catch (IOException ex) {
			throw ex;
		}
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					new ArrayList<>());
    }

    

}
