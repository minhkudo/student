/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.mapper;

import com.example.student.dto.SubTeachStudentDto;
import com.example.student.entity.SubTeachStudent;
import java.util.ArrayList;

/**
 *
 * @author MinhKudo
 */
public class SubTeachStudentMapper {

    public static SubTeachStudentDto toObjectDto(SubTeachStudent subTeachStudent) {
        SubTeachStudentDto subTeachStudentDto = new SubTeachStudentDto();
        subTeachStudentDto.setId(subTeachStudent.getId());
        subTeachStudentDto.setCodeSub(subTeachStudent.getCodeSub());
        subTeachStudentDto.setCodeTeach(subTeachStudent.getCodeTeach());
        subTeachStudentDto.setCodeStudent(subTeachStudent.getCodeStudent());

        return subTeachStudentDto;
    }

    public static SubTeachStudent toObject(SubTeachStudentDto subTeachStudentDto) {
        SubTeachStudent subTeachStudent = new SubTeachStudent();
        subTeachStudent.setId(subTeachStudentDto.getId());
        subTeachStudent.setCodeSub(subTeachStudentDto.getCodeSub());
        subTeachStudent.setCodeTeach(subTeachStudentDto.getCodeTeach());
        subTeachStudent.setCodeStudent(subTeachStudentDto.getCodeStudent());

        return subTeachStudent;
    }

    public static ArrayList<SubTeachStudentDto> toListDto(ArrayList<SubTeachStudent> list) {
        ArrayList<SubTeachStudentDto> arr = new ArrayList<>();
        for (SubTeachStudent subTeachStudent : list) {
            SubTeachStudentDto subTeachStudentDto = new SubTeachStudentDto();
            subTeachStudentDto.setId(subTeachStudent.getId());
            subTeachStudentDto.setCodeSub(subTeachStudent.getCodeSub());
            subTeachStudentDto.setCodeTeach(subTeachStudent.getCodeTeach());
            subTeachStudentDto.setCodeStudent(subTeachStudent.getCodeStudent());
            arr.add(subTeachStudentDto);
        }
        return arr;
    }
}
