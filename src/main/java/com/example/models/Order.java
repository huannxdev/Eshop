/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.models;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import com.example.models.OrderDetails;
import com.example.models.Address;
import java.util.Date;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author NguyenHuan
 */
@Document(collection="Orders")
public class Order implements Serializable{
    @Id
    public String Id;
    public String IdBill;
    public String Email;
    public String UserId;
    public List<OrderDetails> OrderDetails;
    public List<Address> Address;
    public double Total;
    public int Status;
    public Date CreatedDate;
    public Date UpdatedDate;
}
