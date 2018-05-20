/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.domain.ConfigurationReponsitory;
import com.example.models.Configuration;
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
public class ConfigurationService {
    @Autowired
    private ConfigurationReponsitory repository;
    @Autowired
    MongoTemplate mongoTemplate;
    public Configuration Get(){
        List<Configuration> config = repository.findAll();
        if(config.size() == 0)
            return null;
        return config.get(0);
    }
    
    public Configuration Create(Configuration config){
        return repository.save(config);
    }
    
    public Configuration Edit(Configuration config){
        return repository.save(config);
    }
    
    public void Delete(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(query,Configuration.class);
    }
    public int getCurrency(){
        return this.Get().Currency;
    }
}
