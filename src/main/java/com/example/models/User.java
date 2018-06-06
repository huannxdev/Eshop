/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.models;

import com.example.share.Role;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author NguyenHuan
 */
@Document(collection="User")
public class User {
    public User(){
         Id = UUID.randomUUID().toString();
    }
    @Id
    public String Id;
    public String UserName;
    public String UserType;
    public String Gender;
    public String FirstName;
    public String MiddleName;
    public String LastName;
    public String FulName;
    public String Country;
    public String PreferredLanguage;
    public Date DateOfBirth;
    public String Photo;
    public String Status;
    public Date CreatedDate;
    public String CreatedBy;
    public Date ModifiedDate;
    public String ModifiedBy ;
    public List<String> WishList;
    public String Email;
    public String Password;
    public List<Role> Roles;
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
    public List<Role> getRoles() {
    return Roles;
  }
}
