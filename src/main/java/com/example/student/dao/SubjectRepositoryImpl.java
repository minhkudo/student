/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import com.example.student.entity.Subject;
import com.example.student.util.Tool;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MinhKudo
 */
@Repository("subjectRepo")
public class SubjectRepositoryImpl implements SubjectRepositoryIF {

    @Autowired
    private EntityManager entityManager;

    @Override
    public ArrayList<Subject> list(int page, int row, String code, String name) {
        ArrayList<Subject> list = new ArrayList<>();
        String sql = "Select * from subject where 1 = 1 ";
        if (!Tool.checkNull(code)) {
            sql += " AND CODE like ? ";
        }
        if (!Tool.checkNull(name)) {
            sql += " AND NAME like ? ";
        }
        sql += " ORDER BY ID DESC LIMIT ? , ?";
        try {
            Query query = entityManager.createNativeQuery(sql, Subject.class);
            int i = 1;
            if (!Tool.checkNull(code)) {
                query.setParameter(i++, "%" + code + "%");
            }
            if (!Tool.checkNull(name)) {
                query.setParameter(i++, "%" + name + "%");
            }
            query.setParameter(i++, (page - 1) * row);
            query.setParameter(i++, row);
            list = (ArrayList) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
