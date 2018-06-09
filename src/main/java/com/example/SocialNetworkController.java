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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author NguyenHuan
 */
@RestController
@RequestMapping(value = "/socialnetwork")

public class SocialNetworkController {
    @Autowired
    private SocialNetworkService socialService;
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public List<Social> test() {
        return socialService.getSocialNetwork();
    }
}
