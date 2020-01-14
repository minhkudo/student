/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.mapper;

import com.example.student.dto.SubjectDto;
import com.example.student.entity.Subject;
import java.util.ArrayList;

/**
 *
 * @author MinhKudo
 */
public class SubjectMapper {

    public static SubjectDto toObjectDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setCode(subject.getCode());
        subjectDto.setName(subject.getName());
        subjectDto.setNumber(subject.getNumber());
        subjectDto.setYear(subject.getYear());
        return subjectDto;
    }

    public static Subject toObject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setId(subjectDto.getId());
        subject.setCode(subjectDto.getCode());
        subject.setName(subjectDto.getName());
        subject.setNumber(subjectDto.getNumber());
        subject.setYear(subjectDto.getYear());

        return subject;
    }

    public static ArrayList<SubjectDto> toListDto(ArrayList<Subject> list) {
        ArrayList<SubjectDto> arr = new ArrayList<>();
        for (Subject subject : list) {
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setId(subject.getId());
            subjectDto.setCode(subject.getCode());
            subjectDto.setName(subject.getName());
            subjectDto.setNumber(subject.getNumber());
            subjectDto.setYear(subject.getYear());
            arr.add(subjectDto);
        }
        return arr;
    }
}
