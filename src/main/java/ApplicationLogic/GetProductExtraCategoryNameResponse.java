/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationLogic;

import com.example.models.ProductTail;
import java.util.List;

/**
 *
 * @author NguyenHuan
 */
public class GetProductExtraCategoryNameResponse {
    public String Id;
    public String Code;
    public String Name;
    public String IdCategory;
    public String CategoryName;
    public int Status;
    public String Description;
    public String Details;
    public int Discount;
    public List<ProductTail> ProductTails;
    
    //base on list productTail
    public int MinPrice;
    public int MaxPrice;
    public int TotalQuantity;
    public String BasicImage;
    
    public void CalculateProductValues(){
        if(this.ProductTails.size() == 0){
            return;
        }
        this.MinPrice = this.MaxPrice = this.ProductTails.get(0).Price;
        this.BasicImage = this.ProductTails.get(0).Image;
        for(int i =0; i < this.ProductTails.size(); i++){
            //min price
            this.MinPrice = this.MinPrice< ProductTails.get(i).Price?this.MinPrice:this.ProductTails.get(i).Price;
            //maxprice
            this.MaxPrice = this.MaxPrice > this.ProductTails.get(i).Price ? this.MaxPrice : this.ProductTails.get(i).Price;
            //totalquantity
            this.TotalQuantity += this.ProductTails.get(i).Quantity;
        }
    }
}
