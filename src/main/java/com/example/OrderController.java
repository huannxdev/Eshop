/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import ApplicationLogic.GetProductExtraCategoryNameResponse;
import com.example.models.Category;
import com.example.models.Order;
import com.example.models.Product;
import com.example.service.CategoryService;
import com.example.service.OrderService;
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
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author NguyenHuan
 */
@RestController
@RequestMapping(value = "/api/Order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Order> Get(){
        return orderService.GetOrders();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @RequestMapping(value = "/{id}", method = GET)
    public Order Get(@PathVariable("id") String id){
        return orderService.GetOrder(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @RequestMapping(value = "/", method = POST)
    public ResponseEntity<Order>  Create(@RequestBody Order order){
        return new ResponseEntity<Order>(orderService.CreateOrder(order),HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @RequestMapping(value = "/{id}",method= PUT)
    public ResponseEntity<Order>  Put(@PathVariable("id") String id,@RequestBody Order order){
        return new ResponseEntity<Order>(orderService.EditOrder(id, order),HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}",method= DELETE)
    public void Delete(@PathVariable("id") String id){
        orderService.DeleteOrder(id);
    }
}
