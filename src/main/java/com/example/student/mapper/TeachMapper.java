/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.teach.mapper;

import com.example.student.dto.TeachDto;
import com.example.student.entity.Teach;
import java.util.ArrayList;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author MinhKudo
 */
public class TeachMapper {

    public static TeachDto toObjectDto(Teach teach) {
        TeachDto teachDto = new TeachDto();
        teachDto.setId(teach.getId());
        teachDto.setCode(teach.getCode());
        teachDto.setName(teach.getName());
        teachDto.setStatus(teach.getStatus());
        teachDto.setNoti(teach.getNoti());
        return teachDto;
    }

    public static Teach toObject(TeachDto teachDto) {
        Teach teach = new Teach();
        teach.setId(teachDto.getId());
        teach.setCode(teachDto.getCode());
        teach.setName(teachDto.getName());
        teach.setStatus(teachDto.getStatus());
        teach.setNoti(teachDto.getNoti());
        String hash = BCrypt.hashpw(teachDto.getPassword(), BCrypt.gensalt(12));
        teach.setPassword(hash);

        return teach;
    }

    public static ArrayList<TeachDto> toListDto(ArrayList<Teach> list) {
        ArrayList<TeachDto> arr = new ArrayList<>();
        for (Teach teach : list) {
            TeachDto teachDto = new TeachDto();
            teachDto.setId(teach.getId());
            teachDto.setCode(teach.getCode());
            teachDto.setName(teach.getName());
            teachDto.setStatus(teach.getStatus());
            teachDto.setNoti(teach.getNoti());
            arr.add(teachDto);
        }
        return arr;
    }
}
