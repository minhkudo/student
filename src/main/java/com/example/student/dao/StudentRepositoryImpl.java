/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import com.example.student.entity.Student;
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
@Repository("studentRepo")
public class StudentRepositoryImpl implements StudentRepositoryIF {

    @Autowired
    private EntityManager entityManager;

    @Override
    public ArrayList<Student> list(int page, int row, String code, String name, int status) {
        ArrayList<Student> list = new ArrayList<>();
        String sql = "Select * from student where 1 = 1 ";
        if (!Tool.checkNull(code)) {
            sql += " AND CODE like ? ";
        }
        if (!Tool.checkNull(name)) {
            sql += " AND NAME like ? ";
        }
        if (status != -1) {
            sql += " AND STATUS = ? ";
        }
        sql += " ORDER BY ID DESC LIMIT ? , ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Student.class);
            int i = 1;
            if (!Tool.checkNull(code)) {
                query.setParameter(i++, "%" + code + "%");
            }
            if (!Tool.checkNull(name)) {
                query.setParameter(i++, "%" + name + "%");
            }
            if (status != -1) {
                query.setParameter(i++, status);
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
