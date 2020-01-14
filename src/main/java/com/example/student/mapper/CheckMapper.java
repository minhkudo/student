/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.mapper;

import com.example.student.dto.CheckDto;
import com.example.student.entity.Checkin;
import java.util.ArrayList;

/**
 *
 * @author MinhKudo
 */
public class CheckMapper {

    public static CheckDto toObjectDto(Checkin check) {
        CheckDto checkDto = new CheckDto();
        checkDto.setId(check.getId());
        checkDto.setIdSubTeaStu(check.getIdSubTeaStu());
        checkDto.setStatus(check.getStatus());
        checkDto.setTime(check.getTime());

        return checkDto;
    }

    public static Checkin toObject(CheckDto checkDto) {
        Checkin check = new Checkin();
        check.setId(checkDto.getId());
        check.setIdSubTeaStu(checkDto.getIdSubTeaStu());
        check.setStatus(checkDto.getStatus());
        check.setTime(checkDto.getTime());

        return check;
    }

    public static ArrayList<CheckDto> toListDto(ArrayList<Checkin> list) {
        ArrayList<CheckDto> arr = new ArrayList<>();
        for (Checkin check : list) {
            CheckDto checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setIdSubTeaStu(check.getIdSubTeaStu());
            checkDto.setStatus(check.getStatus());
            checkDto.setTime(check.getTime());
            arr.add(checkDto);
        }
        return arr;
    }
}
