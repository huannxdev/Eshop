/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain;

import com.example.models.Category;
import com.example.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author NguyenHuan
 */
@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface CategoryRepository extends MongoRepository<Category, String>{
    
}
