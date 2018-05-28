/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import ApplicationLogic.UserDataDTO;
import com.example.domain.UserRepository;
import com.example.models.User;
import com.example.service.UserService;
import com.example.share.Role;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
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
    @Autowired
    private UserService userService;
    public UserController(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/", method = POST)
    public String Post(@RequestBody UserDataDTO model){
        User user = new User();
        user.UserName = model.Email;
        user.Email = model.Email;
        user.Password = model.Password;
        user.Roles = new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT));
        String token = userService.signup(user);
        return token;  
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/admin", method = POST)
    public String Post_Admin(@RequestBody UserDataDTO model){
        User user = new User();
        user.UserName = model.Email;
        user.Email = model.Email;
        user.Password = model.Password;
        user.Roles = new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN));
        String token = userService.signup(user);
        return token;  
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/login", method = POST)
    public String Login(@RequestBody User model){
        return userService.signin(model.UserName, model.Password);
    }
    @RequestMapping(value = "/{username}", method = GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User GetUser(@PathVariable("username") String username){
        return userService.findByUserName(username);
    }
}
