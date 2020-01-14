/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.service;

import com.example.student.dto.TeachDto;
import com.example.student.request.LoginRequest;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinhKudo
 */
@Service
public interface TeachServiceIF {

    public String login(LoginRequest login);

    public ArrayList<TeachDto> list(int page, int row, String code, String name, int status);

    public TeachDto create(TeachDto dto);

    public TeachDto update(TeachDto dto);

    public TeachDto delete(int id);

    public TeachDto find(int id);
}
