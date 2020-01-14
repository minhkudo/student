/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dto;

import java.sql.Timestamp;

/**
 *
 * @author MinhKudo
 */
public class CheckDto {

    private int id;
    private int idSubTeaStu;
    private Timestamp time;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSubTeaStu() {
        return idSubTeaStu;
    }

    public void setIdSubTeaStu(int idSubTeaStu) {
        this.idSubTeaStu = idSubTeaStu;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
