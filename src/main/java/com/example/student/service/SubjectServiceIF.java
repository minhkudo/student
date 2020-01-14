/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.service;

import com.example.student.dto.SubjectDto;
import com.example.student.dto.SubjectDto;
import com.example.student.entity.Subject;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinhKudo
 */
@Service
public interface SubjectServiceIF {

    public ArrayList<SubjectDto> list(int page, int row, String code, String name);

    public SubjectDto create(SubjectDto dto);

    public SubjectDto update(SubjectDto dto);

    public SubjectDto delete(int id);

    public SubjectDto find(int id);
}
