/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MinhKudo
 */
@Repository("checkRepo")
public class CheckRepositoryImpl implements CheckRepositoryIF {

    @Autowired
    private EntityManager entityManager;
}
