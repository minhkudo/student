/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.dao;

import com.example.student.entity.SubTeach;
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
@Repository("subTeachRepo")
public class SubTeachRepositoryImpl implements SubTeachRepositoryIF {

    @Autowired
    private EntityManager entityManager;

    @Override
    public ArrayList<SubTeach> list(int page, int row, String codeSub, String codeTeach) {
        ArrayList<SubTeach> list = new ArrayList<>();
        String sql = "Select * from map_sub_teach where 1 = 1 ";
        if (!Tool.checkNull(codeSub)) {
            sql += " AND CODE_SUB like ? ";
        }
        if (!Tool.checkNull(codeTeach)) {
            sql += " AND CODE_TEACH like ? ";
        }
        sql += " ORDER BY ID DESC LIMIT ? , ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, SubTeach.class);
            int i = 1;
            if (!Tool.checkNull(codeSub)) {
                query.setParameter(i++, "%" + codeSub + "%");
            }
            if (!Tool.checkNull(codeTeach)) {
                query.setParameter(i++, "%" + codeTeach + "%");
            }
            query.setParameter(i++, (page - 1) * row);
            query.setParameter(i++, row);
            list = (ArrayList) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<SubTeach> listNotReig(int page, int row, String codeSub, String codeTeach, String codeStudent) {
        ArrayList<SubTeach> list = new ArrayList<>();
        String sql = "SELECT * FROM sub_teach_student \n"
                + "WHERE CODE_STUDENT = ? ";
        if (!Tool.checkNull(codeSub)) {
            sql += " AND CODE_SUB like ? ";
        }
        if (!Tool.checkNull(codeTeach)) {
            sql += " AND CODE_TEACH like ? ";
        }
        sql += " ORDER BY ID DESC LIMIT ? , ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, SubTeach.class);
            int i = 1;
            query.setParameter(i++, codeStudent);
            if (!Tool.checkNull(codeSub)) {
                query.setParameter(i++, "%" + codeSub + "%");
            }
            if (!Tool.checkNull(codeTeach)) {
                query.setParameter(i++, "%" + codeTeach + "%");
            }
            query.setParameter(i++, (page - 1) * row);
            query.setParameter(i++, row);
            list = (ArrayList) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    public ArrayList<SubTeach> listDoReig(int page, int row, String codeSub, String codeTeach, String codeStudent) {
        ArrayList<SubTeach> list = new ArrayList<>();
        String sql = "SELECT * FROM map_sub_teach \n"
                + "WHERE CODE_TEACH IS NOT NULL \n"
                + "AND (CODE_SUB NOT IN (SELECT CODE_SUB FROM sub_teach_student WHERE CODE_STUDENT = ?) \n"
                + "OR CODE_TEACH NOT IN (SELECT CODE_TEACH FROM sub_teach_student WHERE CODE_STUDENT = ?)) ";
        if (!Tool.checkNull(codeSub)) {
            sql += " AND CODE_SUB like ? ";
        }
        if (!Tool.checkNull(codeTeach)) {
            sql += " AND CODE_TEACH like ? ";
        }
        sql += " ORDER BY ID DESC LIMIT ? , ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, SubTeach.class);
            int i = 1;
            query.setParameter(i++, codeStudent);
            query.setParameter(i++, codeStudent);
            if (!Tool.checkNull(codeSub)) {
                query.setParameter(i++, "%" + codeSub + "%");
            }
            if (!Tool.checkNull(codeTeach)) {
                query.setParameter(i++, "%" + codeTeach + "%");
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
