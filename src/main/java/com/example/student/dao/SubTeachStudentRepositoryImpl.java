/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import com.example.student.entity.SubTeachStudent;
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
@Repository("subTeachStudentRepo")
public class SubTeachStudentRepositoryImpl implements SubTeachStudentRepositoryIF {

    @Autowired
    private EntityManager entityManager;

    @Override
    public ArrayList<SubTeachStudent> list(int page, int row, String codeSub, String codeTeach, String codeStudent) {
        ArrayList<SubTeachStudent> list = new ArrayList<>();
        String sql = "Select * from sub_teach_student where 1 = 1 ";
        if (!Tool.checkNull(codeSub)) {
            sql += " AND CODE_SUB like ? ";
        }
        if (!Tool.checkNull(codeTeach)) {
            sql += " AND CODE_TEACH like ? ";
        }
        if (!Tool.checkNull(codeStudent)) {
            sql += " AND CODE_STUDENT like ? ";
        }
        sql += " ORDER BY ID DESC LIMIT ? , ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, SubTeachStudent.class);
            int i = 1;
            if (!Tool.checkNull(codeSub)) {
                query.setParameter(i++, "%" + codeSub + "%");
            }
            if (!Tool.checkNull(codeTeach)) {
                query.setParameter(i++, "%" + codeTeach + "%");
            }
            if (!Tool.checkNull(codeStudent)) {
                query.setParameter(i++, "%" + codeStudent + "%");
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
