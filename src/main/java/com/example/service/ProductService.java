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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;

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
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    ProductService(ProductRepository repository,CategoryRepository repositoryCategory){
        this.repository = repository;
        this.repositoryCategory = repositoryCategory;
    }
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
    public GetProductExtraCategoryNameResponse getProduct(String id){
        Product pro = findProductById(id);
        GetProductExtraCategoryNameResponse response = modelMapper.MapProduct(pro);
        return response;
    }
    public Product CreateProduct(Product pro){
        pro.Id = UUID.randomUUID().toString();
        pro.CreatedDate = new Date();
        repository.save(pro);
        return pro;
   
    }
    public Product EditProduct(String id,Product pro){
        pro.Id = id;
        pro.UpdatedDate = new Date();
        return repository.save(pro);
          
    }
    public void DeleteProduct(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Product.class);
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
    public List<GetProductExtraCategoryNameResponse> SearchProduct(String IdCategory, String keyword){
        List<Product> pro = findProductByCategoryAndWord(IdCategory,keyword);
        List<GetProductExtraCategoryNameResponse> listResponse = new ArrayList<GetProductExtraCategoryNameResponse>() ;
        for(int i =0; i < pro.size(); i++){
            listResponse.add(modelMapper.MapProduct(pro.get(i)));
        }
        return listResponse;
    }
    public List<GetProductExtraCategoryNameResponse> ProductWidget(String widget){
        List<Product> products = new ArrayList<Product>();
        List<GetProductExtraCategoryNameResponse> listResponse = new ArrayList<GetProductExtraCategoryNameResponse>();
        if (widget.compareTo("newestProduct") == 0){
           products = repository.findAll(new Sort(Sort.Direction.DESC, "CreatedDate"));
           if(products.size() == 0)
               return null;
        }
        for(int i =0; i < 8 ; i++){
            listResponse.add(modelMapper.MapProduct(products.get(i)));
        }
        return listResponse;
    }
    public List<GetProductExtraCategoryNameResponse> ProductByIdCategory(String idCategory){
        List<Product> products = new ArrayList<Product>();
        List<GetProductExtraCategoryNameResponse> listResponse = new ArrayList<GetProductExtraCategoryNameResponse>();
        Query query = new Query();
        query.addCriteria(Criteria.where("IdCategory").is(idCategory));
        products = mongoTemplate.find(query,Product.class);
        for(int i =0; i < products.size(); i++){
            listResponse.add(modelMapper.MapProduct(products.get(i)));
        }
        return listResponse;
    }
    public Product findProductByCode(String Code){
        Query query = new Query();
        query.addCriteria(Criteria.where("Code").is(Code));
        return  mongoTemplate.findOne(query, Product.class);
    }
    public Product findProductById(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return  mongoTemplate.findOne(query, Product.class);
    }
    public List<Product> findProductByCategoryAndWord(String idCategory, String keyword){
//        
//Query query = new Query(Criteria.where("Name").regex("/^"+keyword+"/i"));
//                                         .and("Name").regex("/^"+keyword+"/i"));
//        List<Criteria> criteria = new ArrayList<>();
//        criteria.add(Criteria.where("IdCategory").is(idCategory));
//        criteria.add(Criteria.where("Name").regex(keyword.replaceAll("\\*", ".*")));
//        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
//        return mongoTemplate.find(query, Product.class);
          return repository.searchNameByWord(keyword);
    }
    
}
