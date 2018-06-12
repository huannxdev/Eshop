/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.domain.CategoryRepository;
import com.example.models.Category;
import com.example.models.Product;
import java.util.List;
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
public class CategoryService {
    @Autowired
    private CategoryRepository repositoryCategory;
    @Autowired
    MongoTemplate mongoTemplate;
    public List<Category> GetCategories(){
        List<Category> categories = repositoryCategory.findAll();
        return categories;
    }
    public Category GetCategoriesById(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Category categories = mongoTemplate.findOne(query, Category.class);
        return categories;
    }
    public List<Category> GetCategoriesByStatus(int status){
        Query query = new Query();
        query.addCriteria(Criteria.where("Status").is(status));
        List<Category> categories = mongoTemplate.find(query, Category.class);
        return categories;
    }
    public Category CreateCategory(Category category){
        category.Id = UUID.randomUUID().toString();
        return repositoryCategory.save(category);
    }
    public Category ReplaceCategory(Category category){
        return repositoryCategory.save(category);
    }
    
    public void DeleteCategory(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Category.class);
    }
}
