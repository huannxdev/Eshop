/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import ApplicationLogic.CheckExistedCodeResponse;
import ApplicationLogic.CreateProductRequest;
import ApplicationLogic.GetProductExtraCategoryNameResponse;
import MapperModel.ModelMapper;
import com.example.domain.CategoryRepository;
import com.example.domain.ProductRepository;
import com.example.models.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author NguyenHuan
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository repositoryCategory;
    
    private static final ModelMapper modelMapper = new ModelMapper();
    
    public List<GetProductExtraCategoryNameResponse> getProducts() {
        List<Product> listProduct = repository.findAll();
        List<GetProductExtraCategoryNameResponse> listResponse = new ArrayList<GetProductExtraCategoryNameResponse>() ;
        for(int i= 0; i < listProduct.size(); i++){
            GetProductExtraCategoryNameResponse response = modelMapper.MapProduct(listProduct.get(i));
            listResponse.add(i,response);
            System.out.println(i);
        }
        return listResponse;
        
    }
    public Product CreateProduct(Product pro){
        pro.Id = UUID.randomUUID().toString();
        repository.save(pro);
        return pro;
   
    }
    public Product EditProduct(String id,Product pro){
        pro.Id = id;
        return repository.save(pro);
          
    }
    public void DeleteProduct(String id){
        repository.deleteById(id);
    }
    public CheckExistedCodeResponse checkExistedCode(String code)
        {
           Product pro = findProductByCode(code);
           CheckExistedCodeResponse response = new CheckExistedCodeResponse();
           if(pro != null){
               response.IsCodeExisted = true;
           }
           else
               response.IsCodeExisted = false;
           return response;       
        }
    @Autowired
    MongoTemplate mongoTemplate;
    public Product findProductByCode(String Code){
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
        query.addCriteria(Criteria.where("Code").is(Code));
        return  mongoTemplate.findOne(query, Product.class);
    }
}
