/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.models;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author NguyenHuan
 */
@Document(collection="Category")
public class Category implements Serializable{
    @Id
    public String Id;
    public String Name;
    public String Description;
    public int Status;
}
