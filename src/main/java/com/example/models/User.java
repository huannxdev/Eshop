/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author NguyenHuan
 */
@Entity
public class User {
    public User(){
         Id = UUID.randomUUID().toString();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String Id;
    public String UserName;
    public int UserType;
    public int Gender;
    public String FirstName;
    public String MiddleName;
    public String LastName;
    public String FulName;
    public String Country;
    public String PreferredLanguage;
    public Date DateOfBirth;
    public String Photo;
    public int Status;
    public Date CreatedDate;
    public String CreatedBy;
    public Date ModifiedDate;
    public String ModifiedBy ;
    public List<String> WishList;
    public String Email;
    public String Password;
    public String getUsername(){
        return UserName;
    }
    public void setUsername(String Username){
        this.UserName = Username;
    }
    public String getPassword(){
        return Password; 
    }
    public void setPassword(String Password){
        this.Password = Password;
    }
}
