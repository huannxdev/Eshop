/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.domain.ProductRepository;
import com.example.models.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author NguyenHuan
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    
    public List getProducts() {
        return repository.findAll();
    }
}
