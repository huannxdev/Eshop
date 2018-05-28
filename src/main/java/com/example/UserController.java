/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.domain.UserRepository;
import com.example.models.User;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author NguyenHuan
 */
@RestController
@RequestMapping(value = "/api/User")
public class UserController {
    private UserRepository repository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;
    public UserController(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/", method = POST)
    public ResponseEntity<User> Post(@RequestBody User model){
        User user = model;
        user.UserName = user.Email;
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.create(user);
        return new ResponseEntity(user,HttpStatus.OK);
    }
}
