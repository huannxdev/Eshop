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
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 *
 * @author NguyenHuan
 */
@RestController
@RequestMapping(value = "/api/Product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<GetProductExtraCategoryNameResponse> Get() {
        return productService.getProducts();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = GET)
    public GetProductExtraCategoryNameResponse Get(@PathVariable("id") String id){
        return productService.getProduct(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/", method = POST)
    public ResponseEntity<Product>  Create(@RequestBody Product pro){
        return new ResponseEntity<Product>(productService.CreateProduct(pro),HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}",method= PUT)
    public ResponseEntity<Product>  Put(@PathVariable("id") String id,@RequestBody Product pro){
        return new ResponseEntity<Product>(productService.EditProduct(id, pro),HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}",method= DELETE)
    public void Delete(@PathVariable("id") String id){
        productService.DeleteProduct(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{code}/checkexistedcode",method= GET)
    public ResponseEntity<CheckExistedCodeResponse> checkExistedCode(@PathVariable("code") String code){
        return new ResponseEntity<CheckExistedCodeResponse>(productService.checkExistedCode(code),HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{idCategory}&{keyword}/searchproduct",method= GET)
    public List<GetProductExtraCategoryNameResponse> searchProduct(@PathVariable("idCategory") String idCategory,@PathVariable("keyword") String keyword){
       return productService.SearchProduct(idCategory, keyword);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{widget}/widget",method= GET)
    public List<GetProductExtraCategoryNameResponse> widgetProduct( @PathVariable("widget") String widget){
        return productService.ProductWidget(widget);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{IdCategory}/category",method= GET)
    public List<GetProductExtraCategoryNameResponse> GetProductBaseOnIDCategory(@PathVariable("IdCategory") String idCategory){
        return productService.ProductByIdCategory(idCategory);
    }
}
