/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import com.example.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MinhKudo
 */
@Repository
public interface StudentDaoIF extends JpaRepository<Student, Integer> {

    @Query(nativeQuery = true, value = "Select * from student where CODE = ?1")
    public Student findStudentByCodeStudent(String code);
}
