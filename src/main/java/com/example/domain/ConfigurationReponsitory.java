/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain;

import com.example.models.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author NguyenHuan
 */
public interface ConfigurationReponsitory extends MongoRepository<Configuration, String> {
    
}
