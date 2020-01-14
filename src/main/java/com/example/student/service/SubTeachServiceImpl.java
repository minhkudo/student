/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.service;

import com.example.student.dao.SubTeachDaoIF;
import com.example.student.dao.SubTeachRepositoryIF;
import com.example.student.dto.SubTeachDto;
import com.example.student.entity.SubTeach;
import com.example.student.mapper.SubTeachMapper;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinhKudo
 */
@Component
public class SubTeachServiceImpl implements SubTeachServiceIF {

    @Autowired
    private SubTeachDaoIF subTeachDaoIF;

    @Autowired
    SubTeachRepositoryIF subTeachRepo;

    @Override
    public ArrayList<SubTeachDto> list(int page, int row, String codeSub, String codeTeach) {
        ArrayList<SubTeach> list = subTeachRepo.list(page, row, codeSub, codeTeach);
        return SubTeachMapper.toListDto(list);
    }

    @Override
    public ArrayList<SubTeachDto> listNotReig(int page, int row, String codeSub, String codeTeach, String codeStudent) {
        ArrayList<SubTeach> list = subTeachRepo.listNotReig(page, row, codeSub, codeTeach, codeStudent);
        return SubTeachMapper.toListDto(list);
    }

    @Override
    public ArrayList<SubTeachDto> listDoReig(int page, int row, String codeSub, String codeTeach, String codeStudent) {
        ArrayList<SubTeach> list = subTeachRepo.listDoReig(page, row, codeSub, codeTeach, codeStudent);
        return SubTeachMapper.toListDto(list);
    }

    @Override
    public SubTeachDto find(int id) {
        Optional<SubTeach> check = subTeachDaoIF.findById(id);
        if (check == null) {
            return null;
        }
        SubTeachDto student = SubTeachMapper.toObjectDto(check.get());
        return student;
    }

    @Override
    public SubTeachDto update(SubTeachDto dto) {
        SubTeach check = subTeachDaoIF.findByCodeSub(dto.getCodeSub());
        if (check == null) {
            return null;
        }

        SubTeach subTeach = SubTeachMapper.toObject(dto);
        SubTeach newSubTeach = subTeachDaoIF.save(subTeach);
        return SubTeachMapper.toObjectDto(newSubTeach);
    }

}
