/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.domain.SocialRepository;
import com.example.models.Social;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author NguyenHuan
 */
@Service
public class SocialNetworkService {
    @Autowired
    private SocialRepository repository;
    
    public List<Social> getSocialNetwork(){
        return repository.findAll();
    }
}
