/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import com.example.student.entity.Checkin;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MinhKudo
 */
@Repository
public interface CheckDaoIF extends JpaRepository<Checkin, Integer> {

    @Query(nativeQuery = true, value = "Select * from `checkin` where ID_STS = ?1")
    public ArrayList<Checkin> findListCheck(int id);
}
