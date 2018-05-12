/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.models.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.service.ProductService;
import java.util.List;

/**
 *
 * @author NguyenHuan
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public List<Product> test() {
        return productService.getProducts();
    }
}
