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

/**
 *
 * @author MinhKudo
 */
@Entity
@Table(name = "map_sub_teach")
public class SubTeach {

    @Id
    @Column(name = "ID", length = 11)
    private int id;

    @Column(name = "CODE_SUB", length = 255)
    private String codeSub;

    @Column(name = "CODE_TEACH", length = 255)
    private String codeTeach;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeSub() {
        return codeSub;
    }

    public void setCodeSub(String codeSub) {
        this.codeSub = codeSub;
    }

    public String getCodeTeach() {
        return codeTeach;
    }

    public void setCodeTeach(String codeTeach) {
        this.codeTeach = codeTeach;
    }

}
