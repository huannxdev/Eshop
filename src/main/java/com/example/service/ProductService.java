/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import ApplicationLogic.CheckExistedCodeResponse;
import ApplicationLogic.GetProductExtraCategoryNameResponse;
import MapperModel.ModelMapper;
import com.example.domain.CategoryRepository;
import com.example.domain.ProductRepository;
import com.example.models.Order;
import com.example.models.OrderDetails;
import com.example.models.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;
import java.util.stream.Collectors;

/**
 *
 * @author NguyenHuan
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository repositoryCategory;
    @Autowired
    private OrderService orderService;
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ProductService(ProductRepository repository, CategoryRepository repositoryCategory) {
        this.repository = repository;
        this.repositoryCategory = repositoryCategory;
    }
    private static final ModelMapper modelMapper = new ModelMapper();

    public List<GetProductExtraCategoryNameResponse> getProducts() {
        List<Product> listProduct = repository.findAll();
        List<GetProductExtraCategoryNameResponse> listResponse = new ArrayList<GetProductExtraCategoryNameResponse>();
        for (int i = 0; i < listProduct.size(); i++) {
            GetProductExtraCategoryNameResponse response = modelMapper.MapProduct(listProduct.get(i));
            listResponse.add(i, response);
        }
        return listResponse;

    }

    public GetProductExtraCategoryNameResponse getProduct(String id) {
        Product pro = findProductById(id);
        GetProductExtraCategoryNameResponse response = modelMapper.MapProduct(pro);
        return response;
    }

    public Product CreateProduct(Product pro) {
        pro.Id = UUID.randomUUID().toString();
        pro.CreatedDate = new Date();
        repository.save(pro);
        return pro;

    }

    public Product EditProduct(String id, Product pro) {
        pro.Id = id;
        pro.UpdatedDate = new Date();
        return repository.save(pro);

    }

    public void DeleteProduct(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Product.class);
    }

    public CheckExistedCodeResponse checkExistedCode(String code) {
        Product pro = findProductByCode(code);
        CheckExistedCodeResponse response = new CheckExistedCodeResponse();
        if (pro != null) {
            response.IsCodeExisted = true;
        } else {
            response.IsCodeExisted = false;
        }
        return response;
    }

    public List<GetProductExtraCategoryNameResponse> SearchProduct(String IdCategory, String keyword) {
        List<Product> pro = findProductByCategoryAndWord(IdCategory, keyword);
        List<GetProductExtraCategoryNameResponse> listResponse = new ArrayList<GetProductExtraCategoryNameResponse>();
        for (int i = 0; i < pro.size(); i++) {
            listResponse.add(modelMapper.MapProduct(pro.get(i)));
        }
        return listResponse;
    }

    public List<GetProductExtraCategoryNameResponse> ProductWidget(String widget) {
        List<Product> products = new ArrayList<Product>();
        List<GetProductExtraCategoryNameResponse> listResponse = new ArrayList<GetProductExtraCategoryNameResponse>();
        if (widget.compareTo("newestProduct") == 0) {
            products = repository.findAll(new Sort(Sort.Direction.DESC, "CreatedDate"));
            if (products.size() == 0) {
                return null;
            }
        } else {
            if (widget.compareTo("topSales") == 0) {
                int sizeListProduct = 8;
                List<Order> orders = orderService.GetOrders();
                List<OrderDetails> details = new ArrayList<OrderDetails>();
                for (int i = 0; i < orders.size(); i++) {
                    details.addAll(orders.get(i).OrderDetails);
                }
                Map<String, Integer> groupList = details.stream().collect(Collectors.groupingBy(OrderDetails::getIdProduct, Collectors.summingInt(OrderDetails::getQuantity)));
                Map<String, Integer> groupListSorted = sortByValue(groupList);
                List<String> listProductId = new ArrayList(groupListSorted.keySet());
                for (int j = 0; j < sizeListProduct; j++) {
                    if( j >= listProductId.size())
                        break;
                    if(findProductById(listProductId.get(j)) == null)
                        sizeListProduct += 1;
                    else
                        products.add(findProductById(listProductId.get(j)));
                }
            } else if (widget.compareTo("topDiscount") == 0) {
                products = repository.findAll(new Sort(Sort.Direction.DESC, "Discount"));
            }
        }
        for (int i = 0; i < 8; i++) {
            listResponse.add(modelMapper.MapProduct(products.get(i)));
        }
        return listResponse;
    }

    public List<GetProductExtraCategoryNameResponse> ProductByIdCategory(String idCategory) {
        List<Product> products = new ArrayList<Product>();
        List<GetProductExtraCategoryNameResponse> listResponse = new ArrayList<GetProductExtraCategoryNameResponse>();
        Query query = new Query();
        query.addCriteria(Criteria.where("IdCategory").is(idCategory));
        products = mongoTemplate.find(query, Product.class);
        for (int i = 0; i < products.size(); i++) {
            listResponse.add(modelMapper.MapProduct(products.get(i)));
        }
        return listResponse;
    }

    public Product findProductByCode(String Code) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Code").is(Code));
        return mongoTemplate.findOne(query, Product.class);
    }

    public Product findProductById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Product.class);
    }

    public List<Product> findProductByCategoryAndWord(String idCategory, String keyword) {
        Query query = new Query();
        if (!idCategory.equals("all"))
        query.addCriteria(Criteria.where("IdCategory").is(idCategory));
        query.addCriteria(Criteria.where("Name").regex(keyword.replaceAll("\\*", ".*")));
        List<Product> productListInCategory = mongoTemplate.find(query, Product.class); 
        return productListInCategory;
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list
                = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
