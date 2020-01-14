/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.service;

import com.example.student.dto.SubTeachDto;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinhKudo
 */
@Service
public interface SubTeachServiceIF {

    public ArrayList<SubTeachDto> list(int page, int row, String codeSub, String codeTeach);

    public ArrayList<SubTeachDto> listNotReig(int page, int row, String codeSub, String codeTeach, String codeStudent);

    public ArrayList<SubTeachDto> listDoReig(int page, int row, String codeSub, String codeTeach, String codeStudent);

    public SubTeachDto find(int id);

    public SubTeachDto update(SubTeachDto dto);
}
