/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.models;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author NguyenHuan
 */
@Document(collection="Configuration")
public class Configuration implements Serializable {
    @Id
    public String Id;
    public List<String> Carousel;
    public int Currency;
    public String ShippingReturnHtml;
    public String ShippingGuideHtml ;
    public String FagHtml;
    public int[] PageSize;
    
}
