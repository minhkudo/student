/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import com.example.student.entity.Subject;
import java.util.ArrayList;

/**
 *
 * @author MinhKudo
 */
public interface SubjectRepositoryIF {

    public ArrayList<Subject> list(int page, int row, String code, String name);
}
