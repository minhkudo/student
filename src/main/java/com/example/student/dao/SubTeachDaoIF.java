/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import com.example.student.entity.SubTeach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MinhKudo
 */
@Repository
public interface SubTeachDaoIF extends JpaRepository<SubTeach, Integer> {

    @Query(nativeQuery = true, value = "Select * from map_sub_teach where CODE_SUB = ?1")
    public SubTeach findByCodeSub(String codeSub);
}
