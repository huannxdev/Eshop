/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationLogic;

import com.example.models.ProductTail;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author NguyenHuan
 */
public class CreateProductRequest implements Serializable{
    
    public String Code;
    public String Name;
    public String Description;
    public String IdCategory;
    public int Status;
    public String Details;
    public int Discount;
    public List<ProductTail> ProductTails;
}
