/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationLogic;

import com.example.share.Role;

/**
 *
 * @author NguyenHuan
 */
public class UserDataDTO {
    public String UserName;
    public String Email;
    public String Password;
    public void setUserName(String userName){
        this.UserName = userName;
    }
    public String getUserName(){
        return this.UserName;
    }
    public String getEmail(){
        return this.Email;
    }
    public void setEmail(String email){
        this.Email = email;
    }
}
