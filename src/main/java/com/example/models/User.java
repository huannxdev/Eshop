/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author NguyenHuan
 */
public class User {
    User(){
        UUID Id = UUID.randomUUID();
    }
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
}
