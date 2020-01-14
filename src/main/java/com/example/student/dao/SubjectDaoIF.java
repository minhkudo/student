/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import com.example.student.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MinhKudo
 */
@Repository
public interface SubjectDaoIF extends JpaRepository<Subject, Integer> {

    @Query(nativeQuery = true, value = "Select * from subject where CODE = ?1")
    public Subject findSubjectByCodeSubject(String code);
}
