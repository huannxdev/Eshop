/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.domain.SocialRepository;
import com.example.models.Product;
import com.example.models.Social;
import java.util.List;
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
public class SocialNetworkService {
    @Autowired
    private SocialRepository repository;
    @Autowired
    MongoTemplate mongoTemplate;
    
    public Social getSocialNetwork(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Social.class);
    }
}
