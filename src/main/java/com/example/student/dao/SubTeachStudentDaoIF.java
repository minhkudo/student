/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import com.example.student.entity.SubTeachStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MinhKudo
 */
@Repository
public interface SubTeachStudentDaoIF extends JpaRepository<SubTeachStudent, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM sub_teach_student WHERE CODE_SUB = ?1 AND CODE_TEACH = ?2 AND CODE_STUDENT = ?3")
    public SubTeachStudent findSTS(String codeSub, String codeTeach, String codeStudent);
}
