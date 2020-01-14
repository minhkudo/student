/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.service;

import com.example.student.dto.StudentDto;
import com.example.student.request.LoginRequest;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinhKudo
 */
@Service
public interface StudentServiceIF {

    public String login(LoginRequest login);

    public ArrayList<StudentDto> list(int page, int row, String code, String name, int status);

    public StudentDto create(StudentDto dto);

    public StudentDto update(StudentDto dto);

    public StudentDto delete(int id);
    
    public StudentDto find(int id);
}
