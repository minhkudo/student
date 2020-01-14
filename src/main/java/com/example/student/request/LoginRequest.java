/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author MinhKudo
 */
public class LoginRequest {

    @NotNull(message = "Code is required")
    @NotEmpty(message = "Code is required")
    String code;
    
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    String password;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
