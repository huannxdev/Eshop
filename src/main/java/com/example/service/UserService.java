/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.domain.UserRepository;
import com.example.models.User;
import com.example.exception.CustomException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.security.JwtTokenProvider;
import com.example.share.Role;
import java.util.List;

/**
 *
 * @author NguyenHuan
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12);
//    }
//    @Bean
//    public JwtTokenProvider jwtTokenProvider(){
//        return new JwtTokenProvider();
//    }
    public User findByUserName(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("UserName").is(username));
        return mongoTemplate.findOne(query, User.class);
    }

    public User create(User user) {
        if (user.Id == null) {
            user.Id = UUID.randomUUID().toString();
        }
        repository.save(user);
        return user;
    }

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, findByUserName(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
    public String signinAdmin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if(findByUserName(username).getRoles().contains(Role.ROLE_ADMIN) == false)
                throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
            return jwtTokenProvider.createToken(username, findByUserName(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user) {
        if (findByUserName(user.UserName) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            create(user);
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
    public List<User> GetAllUser(){
        return repository.findAll();
    }
    
    public User getUserById(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }
    
    public void deleteUserById(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, User.class);
    }
    
    public User updateUser(User user){
        return repository.save(user);
    }
}
