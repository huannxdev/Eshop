/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.domain.CategoryRepository;
import com.example.models.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author NguyenHuan
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repositoryCategory;
    public List<Category> GetCategories(){
        List<Category> categories = repositoryCategory.findAll();
        return categories;
    }
}
