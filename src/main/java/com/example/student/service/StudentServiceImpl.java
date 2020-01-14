/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.service;

import com.example.student.dao.StudentDaoIF;
import com.example.student.dao.StudentRepositoryIF;
import com.example.student.dto.StudentDto;
import com.example.student.entity.Student;
import com.example.student.mapper.StudentMapper;
import com.example.student.request.LoginRequest;
import com.example.student.util.JwtUltis;
import com.example.student.util.Tool;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MinhKudo
 */
@Component
public class StudentServiceImpl implements StudentServiceIF {

    @Autowired
    private StudentDaoIF studentDaoIF;

    @Autowired
    StudentRepositoryIF studentRepo;

    @Override
    public String login(LoginRequest login) {
        Student student = studentDaoIF.findStudentByCodeStudent(login.getCode());
        if (student == null) {
            return null;
        }

        if (student.getStatus() == 0) {
            return null;
        }

        boolean result = BCrypt.checkpw(login.getPassword(), student.getPassword());
        if (!result) {
            return null;
        }

        String token = JwtUltis.generateToken(login.getCode(), "MEMBER");
        return token;

    }

    @Override
    public ArrayList<StudentDto> list(int page, int row, String code, String name, int status) {
        ArrayList<Student> list = studentRepo.list(page, row, code, name, status);
        return StudentMapper.toListDto(list);
    }

    @Transactional
    @Override
    public StudentDto create(StudentDto dto) {
        Student check = studentDaoIF.findStudentByCodeStudent(dto.getCode());
        if (check != null) {
            return null;
        }

        Student student = StudentMapper.toObject(dto);
        Student newStudent = studentDaoIF.save(student);
        return StudentMapper.toObjectDto(newStudent);
    }

    @Transactional
    @Override
    public StudentDto update(StudentDto dto) {
        Student check = studentDaoIF.findStudentByCodeStudent(dto.getCode());
        if (check == null) {
            return null;
        }

        Student student = new Student();

        if (!Tool.checkNull(dto.getPassword())) {
            student = StudentMapper.toObject(dto);
        } else {
            student.setId(dto.getId());
            student.setCode(dto.getCode());
            student.setName(dto.getName());
            student.setPassword(check.getPassword());
            student.setNoti(dto.getNoti());
            student.setStatus(dto.getStatus());
        }

        Student newStudent = studentDaoIF.save(student);
        return StudentMapper.toObjectDto(newStudent);
    }

    @Transactional
    @Override
    public StudentDto delete(int id) {
        Optional<Student> check = studentDaoIF.findById(id);
        if (check == null) {
            return null;
        }
        studentDaoIF.deleteById(id);
        StudentDto student = StudentMapper.toObjectDto(check.get());
        return student;
    }

    @Override
    public StudentDto find(int id) {
        Optional<Student> check = studentDaoIF.findById(id);
        if (check == null) {
            return null;
        }
        StudentDto student = StudentMapper.toObjectDto(check.get());
        return student;
    }

}
