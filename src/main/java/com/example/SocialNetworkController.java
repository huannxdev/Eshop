/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.models.Social;
import com.example.service.SocialNetworkService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author NguyenHuan
 */
@RestController
@RequestMapping(value = "api/socialnetwork")

public class SocialNetworkController {
    @Autowired
    private SocialNetworkService socialService;
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Social gets(@PathVariable("id") String id) {
        return socialService.getSocialNetwork(id);
    }
}
