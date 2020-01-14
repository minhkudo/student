/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.mapper;

import com.example.student.dto.SubTeachDto;
import com.example.student.entity.SubTeach;
import java.util.ArrayList;

/**
 *
 * @author MinhKudo
 */
public class SubTeachMapper {
    
    public static SubTeachDto toObjectDto(SubTeach subTeach) {
        SubTeachDto subTeachDto = new SubTeachDto();
        subTeachDto.setId(subTeach.getId());
        subTeachDto.setCodeSub(subTeach.getCodeSub());
        subTeachDto.setCodeTeach(subTeach.getCodeTeach());

        return subTeachDto;
    }

    public static SubTeach toObject(SubTeachDto subTeachDto) {
        SubTeach subTeach = new SubTeach();
        subTeach.setId(subTeachDto.getId());
        subTeach.setCodeSub(subTeachDto.getCodeSub());
        subTeach.setCodeTeach(subTeachDto.getCodeTeach());

        return subTeach;
    }

    public static ArrayList<SubTeachDto> toListDto(ArrayList<SubTeach> list) {
        ArrayList<SubTeachDto> arr = new ArrayList<>();
        for (SubTeach subTeach : list) {
            SubTeachDto subTeachDto = new SubTeachDto();
            subTeachDto.setId(subTeach.getId());
            subTeachDto.setCodeSub(subTeach.getCodeSub());
            subTeachDto.setCodeTeach(subTeach.getCodeTeach());
            arr.add(subTeachDto);
        }
        return arr;
    }
}
