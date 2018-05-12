/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author NguyenHuan
 */
@Document(collection="Social")
public class Social {
    @Id
    public String Id;
    
    public String Address;
    
    public String Hostline;
    
    public String Email;
    
    public String linkFB;
    
    public String linkTwitter;
    
    public String linkInstagram;
    
    public String linkGooglePlus;
    
    public String linkPinterest;
    
}
