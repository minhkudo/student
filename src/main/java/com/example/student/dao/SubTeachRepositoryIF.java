/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import com.example.student.entity.SubTeach;
import java.util.ArrayList;

/**
 *
 * @author MinhKudo
 */
public interface SubTeachRepositoryIF {

    public ArrayList<SubTeach> list(int page, int row, String codeSub, String codeTeach);

    public ArrayList<SubTeach> listNotReig(int page, int row, String codeSub, String codeTeach, String codeStudent);

    public ArrayList<SubTeach> listDoReig(int page, int row, String codeSub, String codeTeach, String codeStudent);
}
