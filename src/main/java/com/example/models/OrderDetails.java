/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.models;

import java.io.Serializable;

/**
 *
 * @author NguyenHuan
 */
public class OrderDetails implements Serializable {
    public String IdProduct;
    public String Code;
    public String NameProduct;
    public int Quantity;
    public double Price;
    public double TotalPrice;
    public String Size;
    public String Color;
    
    public int getQuantity(){
        return this.Quantity;
    }
    public String getIdProduct(){
        return this.IdProduct;
    }
}
