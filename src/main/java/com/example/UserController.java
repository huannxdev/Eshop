/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import ApplicationLogic.CheckWishProductResponse;
import ApplicationLogic.GetProductExtraCategoryNameResponse;
import ApplicationLogic.UserDataDTO;
import ch.qos.logback.core.net.server.Client;
import com.example.domain.UserRepository;
import com.example.models.User;
import com.example.service.*;
import com.example.share.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author NguyenHuan
 */
@RestController
@RequestMapping(value = "/api/User")
public class UserController {
    private UserRepository repository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    public UserController(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/", method = POST)
    public String Post(@RequestBody UserDataDTO model){
        User user = new User();
        user.UserName = model.Email;
        user.Email = model.Email;
        user.Password = model.Password;
        user.Roles = new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT));
        String token = userService.signup(user);
        return token;  
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin", method = POST)
    public String Post_Admin(@RequestBody UserDataDTO model){
        User user = new User();
        user.UserName = model.Email;
        user.Email = model.Email;
        user.Password = model.Password;
        user.Roles = new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN));
        String token = userService.signup(user);
        return token;  
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/login", method = POST)
    public String Login(@RequestBody User model){
        return userService.signin(model.UserName, model.Password);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/loginAdmin", method = POST)
    public String LoginAdmin(@RequestBody User model){
        return userService.signinAdmin(model.UserName, model.Password);
    }
    
    @RequestMapping(value = "/{username}", method = GET)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT') ")
    @CrossOrigin(origins = "http://localhost:4200")
    public User GetUser(@PathVariable("username") String username){
        return userService.findByUserName(username);
    }
    @RequestMapping(value = "/id={id}", method = GET)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT') ")
    @CrossOrigin(origins = "http://localhost:4200")
    public User GetUserById(@PathVariable("id") String id){
        return userService.getUserById(id);
    }
    @RequestMapping(value = "/", method = GET)
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> GetALLUser(){
        return userService.GetAllUser();
    }
    
    @RequestMapping(value = "/", method = DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    public void Delete(@PathVariable("id") String id){
        userService.deleteUserById(id);
    }
    
    @RequestMapping(value = "/{id}",method= PUT)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> Put(@PathVariable("id") String id,@RequestBody User user){
        return new ResponseEntity<User>(userService.updateUser(user),HttpStatus.OK);
    }
    
    @RequestMapping(value = "/CheckUserExist/{terms}",method= GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> CheckUserExist(@PathVariable("terms") String terms){
        return new ResponseEntity<User>(userService.findByUserName(terms),HttpStatus.OK);
    }
    
    @RequestMapping(value = "/GetWishList/{username}",method= GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public List<GetProductExtraCategoryNameResponse> GetWishList(@PathVariable("username") String username){
        User user = userService.findByUserName(username);
        List<GetProductExtraCategoryNameResponse> productWishList = new ArrayList<GetProductExtraCategoryNameResponse>();
        for(int i= 0; i < user.WishList.size(); i++){
            productWishList.add(productService.getProduct(user.WishList.get(i)));
        }
        return productWishList;
    }
    
    @RequestMapping(value = "/{username}/product/{idProduct}/removeWishProduct",method= DELETE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> RemoveWishProduct(@PathVariable("username") String username, @PathVariable("idProduct") String idProduct){
        User user = userService.findByUserName(username);
        if(user != null)
        {
            user.WishList.remove(idProduct);
            return new ResponseEntity<User>(userService.updateUser(user),HttpStatus.OK);
        }
        return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value = "/{username}/product/{idProduct}/addWishProduct",method= GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> AddWishProduct(@PathVariable("username") String username, @PathVariable("idProduct") String idProduct){
        User user = userService.findByUserName(username);
        if(user != null){
            if(user.WishList == null)
                user.WishList = new ArrayList<String>();
            user.WishList.add(idProduct);
            return new ResponseEntity<User>(userService.updateUser(user),HttpStatus.OK);
        }
        return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value = "/{username}/product/{idProduct}/checkWishProduct",method= GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CheckWishProductResponse> CheckWishProduct(@PathVariable("username") String username, @PathVariable("idProduct") String idProduct){
        User user = userService.findByUserName(username);
        if(user != null){
            CheckWishProductResponse result = new CheckWishProductResponse();
            if(user.WishList != null)
                result.IsWishProduct = user.WishList.contains(idProduct);
            else
                result.IsWishProduct = false;
             return new ResponseEntity<CheckWishProductResponse>(result,HttpStatus.OK);
        }
        return new ResponseEntity<CheckWishProductResponse>(HttpStatus.BAD_REQUEST);
    }
}
