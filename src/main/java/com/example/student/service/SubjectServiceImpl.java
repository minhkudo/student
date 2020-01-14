/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.service;

import com.example.student.dao.SubTeachDaoIF;
import com.example.student.dao.SubjectDaoIF;
import com.example.student.dao.SubjectRepositoryIF;
import com.example.student.dto.SubTeachDto;
import com.example.student.dto.SubjectDto;
import com.example.student.dto.SubjectDto;
import com.example.student.entity.SubTeach;
import com.example.student.entity.Subject;
import com.example.student.mapper.SubjectMapper;
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
public class SubjectServiceImpl implements SubjectServiceIF {
    
    @Autowired
    private SubjectDaoIF teachDaoIF;
    
    @Autowired
    SubjectRepositoryIF subjectRepo;
    
    @Autowired
    private SubTeachDaoIF subTeachDaoIF;
    
    @Override
    public ArrayList<SubjectDto> list(int page, int row, String code, String name) {
        ArrayList<Subject> list = subjectRepo.list(page, row, code, name);
        return SubjectMapper.toListDto(list);
    }
    
    @Transactional
    @Override
    public SubjectDto create(SubjectDto dto) {
        Subject check = teachDaoIF.findSubjectByCodeSubject(dto.getCode());
        if (check != null) {
            return null;
        }
        
        Subject subject = SubjectMapper.toObject(dto);
        Subject newSubject = teachDaoIF.save(subject);
        if (newSubject != null) {
            SubTeach subTeach = new SubTeach();
            subTeach.setCodeSub(newSubject.getCode());
            subTeachDaoIF.save(subTeach);
        }
        return SubjectMapper.toObjectDto(newSubject);
    }
    
    @Transactional
    @Override
    public SubjectDto update(SubjectDto dto) {
        Subject check = teachDaoIF.findSubjectByCodeSubject(dto.getCode());
        if (check == null) {
            return null;
        }
        
        Subject subject = SubjectMapper.toObject(dto);
        Subject newSubject = teachDaoIF.save(subject);
        return SubjectMapper.toObjectDto(newSubject);
    }
    
    @Transactional
    @Override
    public SubjectDto delete(int id) {
        Optional<Subject> check = teachDaoIF.findById(id);
        if (check == null) {
            return null;
        }
        teachDaoIF.deleteById(id);
        SubTeach subTeach = subTeachDaoIF.findByCodeSub(check.get().getCode());
        subTeachDaoIF.deleteById(subTeach.getId());
        SubjectDto student = SubjectMapper.toObjectDto(check.get());
        return student;
    }
    
    @Override
    public SubjectDto find(int id) {
        Optional<Subject> check = teachDaoIF.findById(id);
        if (check == null) {
            return null;
        }
        SubjectDto student = SubjectMapper.toObjectDto(check.get());
        return student;
    }
    
}
