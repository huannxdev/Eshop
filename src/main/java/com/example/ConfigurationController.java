/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.models.Configuration;
import com.example.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/api/Configuration")
public class ConfigurationController {
    @Autowired
    private ConfigurationService configurationService;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Configuration> Get(){
       return new ResponseEntity<Configuration>(configurationService.Get(),HttpStatus.OK);   
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Configuration> Post(@RequestBody Configuration config){
        return new ResponseEntity<Configuration>(configurationService.Create(config),HttpStatus.OK);   
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Configuration> Put(@PathVariable("id") String id,@RequestBody Configuration config ){
        return new ResponseEntity<Configuration>(configurationService.Edit(config),HttpStatus.OK); 
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete(@PathVariable("id") String id){
        configurationService.Delete(id);
    }
   
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/metaData", method = RequestMethod.GET)
    public ResponseEntity<Integer> MetaData(){
        return new ResponseEntity<Integer>(configurationService.getCurrency(),HttpStatus.OK);
    }
}
