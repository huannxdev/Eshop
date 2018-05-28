/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.share;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author NguyenHuan
 */
public enum Role implements GrantedAuthority {
  ROLE_ADMIN, ROLE_CLIENT;
public String getAuthority() {
    return name();
  }
}