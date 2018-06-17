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

import com.example.domain.OrderRepository;
import com.example.models.Order;
import com.example.models.Product;
import java.util.ArrayList;
import java.util.Collections;
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
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    OrderService(OrderRepository repository){
        this.repository = repository;
    }
    private static final ModelMapper modelMapper = new ModelMapper();
    public List<Order> GetOrders() {
        List<Order> listOrder = repository.findAll(new Sort(Sort.Direction.DESC, "CreatedDate"));
        return listOrder;
        
    }
    public Order GetOrder(String id){
        Order order = findOrderById(id);
        return order;
    }
    public Order CreateOrder(Order order){
        order.Id = UUID.randomUUID().toString();
        order.CreatedDate = new Date();
        repository.save(order);
        return order;
    }
    public Order EditOrder(String id, Order order){
        order.Id = id;
        order.UpdatedDate = new Date();
        return repository.save(order);
          
    }
    public void DeleteOrder(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Order.class);
    }
  
    public Order findOrderByCode(String Code){
        Query query = new Query();
        query.addCriteria(Criteria.where("Code").is(Code));
        return  mongoTemplate.findOne(query, Order.class);
    }
    public Order findOrderById(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return  mongoTemplate.findOne(query, Order.class);
    }
    
    public List<Order> findOrderByUserId(String userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("UserId").is(userId)).with(new Sort(Sort.Direction.DESC,"CreatedDate"));
        return mongoTemplate.find(query,Order.class);
    }
}
