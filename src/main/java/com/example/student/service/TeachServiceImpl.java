/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.service;

import com.example.student.dao.TeachDaoIF;
import com.example.student.dao.TeachRepositoryIF;
import com.example.student.dto.TeachDto;
import com.example.student.entity.Teach;
import com.example.student.request.LoginRequest;
import com.example.student.util.JwtUltis;
import com.example.student.util.Tool;
import com.example.teach.mapper.TeachMapper;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MinhKudo
 */
@Component
public class TeachServiceImpl implements TeachServiceIF {

    @Autowired
    private TeachDaoIF teachDaoIF;

    @Autowired
    TeachRepositoryIF teachRepo;

    @Override
    public String login(LoginRequest login) {
        Teach teach = teachDaoIF.findTeachByCodeTeach(login.getCode());
        if (teach == null) {
            return null;
        }

        boolean result = BCrypt.checkpw(login.getPassword(), teach.getPassword());
        if (!result) {
            return null;
        }

        String token = JwtUltis.generateToken(login.getCode(), "USER");
        return token;
    }

    @Override
    public ArrayList<TeachDto> list(int page, int row, String code, String name, int status) {
        ArrayList<Teach> list = teachRepo.list(page, row, code, name, status);
        return TeachMapper.toListDto(list);
    }

    @Transactional
    @Override
    public TeachDto create(TeachDto dto) {
        Teach check = teachDaoIF.findTeachByCodeTeach(dto.getCode());
        if (check != null) {
            return null;
        }

        Teach teach = TeachMapper.toObject(dto);
        Teach newTeach = teachDaoIF.save(teach);
        return TeachMapper.toObjectDto(newTeach);
    }

    @Transactional
    @Override
    public TeachDto update(TeachDto dto) {
        Teach check = teachDaoIF.findTeachByCodeTeach(dto.getCode());
        if (check == null) {
            return null;
        }

        Teach teach = new Teach();

        if (!Tool.checkNull(dto.getPassword())) {
            teach = TeachMapper.toObject(dto);
        } else {
            teach.setId(dto.getId());
            teach.setCode(dto.getCode());
            teach.setName(dto.getName());
            teach.setPassword(check.getPassword());
            teach.setNoti(dto.getNoti());
            teach.setStatus(dto.getStatus());
        }
        Teach newTeach = teachDaoIF.save(teach);
        return TeachMapper.toObjectDto(newTeach);
    }

    @Transactional
    @Override
    public TeachDto delete(int id) {
        Optional<Teach> check = teachDaoIF.findById(id);
        if (check == null) {
            return null;
        }
        teachDaoIF.deleteById(id);
        TeachDto student = TeachMapper.toObjectDto(check.get());
        return student;
    }

    @Override
    public TeachDto find(int id) {
        Optional<Teach> check = teachDaoIF.findById(id);
        if (check == null) {
            return null;
        }
        TeachDto student = TeachMapper.toObjectDto(check.get());
        return student;
    }

}
