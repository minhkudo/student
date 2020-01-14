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
@Table(name = "subject")
public class Subject {

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

    @Column(name = "NUMBER", length = 11)
    private int number;

    @Column(name = "YEAR", length = 11)
    private int year;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
