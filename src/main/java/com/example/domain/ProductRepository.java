/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain;

import com.example.models.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author NguyenHuan
 */
@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface ProductRepository extends MongoRepository<Product, String> {
   @Query("{Name: {$regex: ?0}}")
   List<Product> searchNameByWord(String keyword);
}
