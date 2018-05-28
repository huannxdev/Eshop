/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.domain.UserRepository;
import com.example.models.User;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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
    public User findByUserName(String username){
        Query query = new Query();
        query.addCriteria(Criteria.where("UserName").is(username));
        return mongoTemplate.findOne(query, User.class);
    }
    public User create(User user){
        if(user.Id == null){
            user.Id = UUID.randomUUID().toString();
        }
        repository.save(user);
        return user;
    }
}
