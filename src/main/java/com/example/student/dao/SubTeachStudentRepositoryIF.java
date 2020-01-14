/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import com.example.student.entity.SubTeachStudent;
import java.util.ArrayList;

/**
 *
 * @author MinhKudo
 */
public interface SubTeachStudentRepositoryIF {

    public ArrayList<SubTeachStudent> list(int page, int row, String codeSub, String codeTeach, String codeStudent);
}
