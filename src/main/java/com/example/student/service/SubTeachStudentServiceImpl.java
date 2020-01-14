/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.service;

import com.example.student.dao.SubTeachStudentDaoIF;
import com.example.student.dao.SubTeachStudentRepositoryIF;
import com.example.student.dto.SubTeachStudentDto;
import com.example.student.entity.SubTeachStudent;
import com.example.student.mapper.SubTeachStudentMapper;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MinhKudo
 */
@Component
public class SubTeachStudentServiceImpl implements SubTeachStudentServiceIF {

    @Autowired
    private SubTeachStudentDaoIF subTeachStudentDaoIF;

    @Autowired
    private SubTeachStudentRepositoryIF subTeachStudentRepo;

    @Override
    public ArrayList<SubTeachStudentDto> list(int page, int row, String codeSub, String codeTeach, String codeStudent) {
        ArrayList<SubTeachStudent> list = subTeachStudentRepo.list(page, row, codeSub, codeTeach, codeStudent);
        return SubTeachStudentMapper.toListDto(list);
    }

    @Transactional
    @Override
    public SubTeachStudentDto create(SubTeachStudentDto dto) {
        SubTeachStudent subTeachStudent = SubTeachStudentMapper.toObject(dto);
        SubTeachStudent newSubTeachStudent = subTeachStudentDaoIF.save(subTeachStudent);
        return SubTeachStudentMapper.toObjectDto(newSubTeachStudent);
    }

    @Transactional
    @Override
    public SubTeachStudentDto delete(SubTeachStudentDto dto) {
        SubTeachStudent check = subTeachStudentDaoIF.findSTS(dto.getCodeSub(), dto.getCodeTeach(), dto.getCodeStudent());
        if (check == null) {
            return null;
        }
        subTeachStudentDaoIF.deleteById(check.getId());
        SubTeachStudentDto subTeachStudentDto = SubTeachStudentMapper.toObjectDto(check);
        return subTeachStudentDto;
    }

}
