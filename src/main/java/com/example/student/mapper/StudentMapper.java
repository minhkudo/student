/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.mapper;

import com.example.student.dto.StudentDto;
import com.example.student.entity.Student;
import java.util.ArrayList;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author MinhKudo
 */
public class StudentMapper {

    public static StudentDto toObjectDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setCode(student.getCode());
        studentDto.setName(student.getName());
        studentDto.setStatus(student.getStatus());
        studentDto.setNoti(student.getNoti());
        return studentDto;
    }

    public static Student toObject(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setCode(studentDto.getCode());
        student.setName(studentDto.getName());
        student.setStatus(studentDto.getStatus());
        student.setNoti(studentDto.getNoti());
        String hash = BCrypt.hashpw(studentDto.getPassword(), BCrypt.gensalt(12));
        student.setPassword(hash);

        return student;
    }

    public static ArrayList<StudentDto> toListDto(ArrayList<Student> list) {
        ArrayList<StudentDto> arr = new ArrayList<>();
        for (Student student : list) {
            StudentDto studentDto = new StudentDto();
            studentDto.setId(student.getId());
            studentDto.setCode(student.getCode());
            studentDto.setName(student.getName());
            studentDto.setStatus(student.getStatus());
            studentDto.setNoti(student.getNoti());
            arr.add(studentDto);
        }
        return arr;
    }
}
