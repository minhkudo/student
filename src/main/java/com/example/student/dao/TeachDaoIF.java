/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import com.example.student.entity.Teach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MinhKudo
 */
@Repository
public interface TeachDaoIF extends JpaRepository<Teach, Integer>{

    @Query(nativeQuery = true, value = "Select * from teach where CODE = ?1")
    public Teach findTeachByCodeTeach(String code);
}
