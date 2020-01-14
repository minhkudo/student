/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.service;

import com.example.student.dto.SubTeachStudentDto;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinhKudo
 */
@Service
public interface SubTeachStudentServiceIF {

    public ArrayList<SubTeachStudentDto> list(int page, int row, String codeSub, String codeTeach, String codeStudent);

    public SubTeachStudentDto create(SubTeachStudentDto dto);

    public SubTeachStudentDto delete(SubTeachStudentDto dto);
}
