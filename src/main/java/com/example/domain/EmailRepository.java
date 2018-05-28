package com.example.domain;

import com.example.models.Email;
import com.example.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Uzumaki
 */
@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface EmailRepository extends MongoRepository<Email, String> {
    
}
