/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapperModel;

import ApplicationLogic.GetProductExtraCategoryNameResponse;
import com.example.models.Product;
import java.util.List;

/**
 *
 * @author NguyenHuan
 */
public class ModelMapper {
    public GetProductExtraCategoryNameResponse MapProduct(Product pro){
        GetProductExtraCategoryNameResponse response = new GetProductExtraCategoryNameResponse();
        if(pro == null)
            return null;
        response.Id = pro.Id;
        response.Code = pro.Code;
        response.IdCategory = pro.IdCategory;
        response.Description = pro.Description;
        response.Details = pro.Details;
        response.Discount = pro.Discount;
        response.Name = pro.Code;
        response.Status = pro.Status;
        response.ProductTails = pro.ProductTails;
        response.CalculateProductValues();
        return response;
    }
}
