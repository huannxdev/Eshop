/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.models;
import com.example.share.ProductStatus;
import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 *
 * @author NguyenHuan
 */
@Document(collection="Product")
public class Product implements Serializable {
    @Id
    public String Id;
    public String Code;

        
        public String Name ;

        public String Description ;

        
        public String IdCategory ;

        public int Status ;

        public String Details ;

        public int Discount ;

        public List<ProductTail> ProductTails ;

        public String SEODescription ;

        public String SEOKeyWords ;

        public String SEOTitle ;
    
}
