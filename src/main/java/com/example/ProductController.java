/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import ApplicationLogic.CheckExistedCodeResponse;
import ApplicationLogic.CreateProductRequest;
import ApplicationLogic.GetProductExtraCategoryNameResponse;
import com.example.models.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.service.ProductService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author NguyenHuan
 */
@RestController
@RequestMapping(value = "/api")
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/Product", method = RequestMethod.GET)
    public List<GetProductExtraCategoryNameResponse> Get() {
        return productService.getProducts();
    }
    @RequestMapping(value = "/Product/{id}", method = GET)
    public Product Get(@PathVariable("id") String id){
        return null;
    }
    @RequestMapping(value = "/Product", method = POST)
    public ResponseEntity<Product>  Create(@RequestBody Product pro){
        return new ResponseEntity<Product>(productService.CreateProduct(pro),HttpStatus.OK);
    }
    @RequestMapping(value = "/Product",method= PUT)
    public ResponseEntity<Product>  Put(String id,@RequestBody Product pro){
        return new ResponseEntity<Product>(productService.EditProduct(id, pro),HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/Product",method= DELETE)
    public void Delete(String id){
        productService.DeleteProduct(id);
    }
    @RequestMapping(value = "/{code}/checkexistedcode",method= GET)
    public ResponseEntity<CheckExistedCodeResponse> checkExistedCode(@PathVariable("code") String code){
        return new ResponseEntity<CheckExistedCodeResponse>(productService.checkExistedCode(code),HttpStatus.OK);
    }
    @RequestMapping(value = "/{idCategory}&{keyword}/searchproduct",method= GET)
    public List<GetProductExtraCategoryNameResponse> searchProduct(@PathVariable("idCategory") String idCategory,@PathVariable("keyword") String keyword){
        return null;
    }
    
}
