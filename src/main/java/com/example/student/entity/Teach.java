/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author MinhKudo
 */
@Entity
@Table(name = "teach")
public class Teach {

    @Id
    @Column(name = "ID", length = 11, nullable = false)
    private int id;

    @NotNull(message = "Code is required")
    @NotEmpty(message = "Code is required")
    @Column(name = "CODE", length = 255)
    private String code;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Column(name = "NAME", length = 255)
    private String name;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Column(name = "PASSWORD", length = 255)
    private String password;

    @Column(name = "STATUS", length = 11)
    private int status;

    @Column(name = "NOTI", length = 255)
    private String noti;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNoti() {
        return noti;
    }

    public void setNoti(String noti) {
        this.noti = noti;
    }
}
