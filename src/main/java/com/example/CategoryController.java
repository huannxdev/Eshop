/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.models.Category;
import com.example.models.Product;
import com.example.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author NguyenHuan
 */
@RestController
@RequestMapping(value = "/api/Category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Category> Get(){
        return categoryService.GetCategories();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Category> Get(@PathVariable("id")String id){
        return categoryService.GetCategoriesById(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{status}/status", method = RequestMethod.GET)
    public List<Category> Get(@PathVariable("status") boolean status ){
        return categoryService.GetCategoriesByStatus(status);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Category> Post(@RequestBody Category category){
        return new ResponseEntity<Category>(categoryService.CreateCategory(category),HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Category> Put(@PathVariable("id") String id,@RequestBody Category category){
        return new ResponseEntity<Category>(categoryService.ReplaceCategory(category),HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void Delete(@PathVariable("id") String id){
        categoryService.DeleteCategory(id);
    }
}
