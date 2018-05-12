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
@Document(collection="Configuration")
public class FeedBack implements Serializable {
    @Id
    public String Id;
    public String ToEmail;
    public String Subject;
    public String Content;
    public boolean Status;
}
