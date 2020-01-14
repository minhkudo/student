/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.service;

import com.example.student.dao.CheckDaoIF;
import com.example.student.dto.CheckDto;
import com.example.student.entity.Checkin;
import com.example.student.mapper.CheckMapper;
import com.example.student.model.RequestJsonClient;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MinhKudo
 */
@Component
public class CheckServiceImpl implements CheckServiceIF {

    @Autowired
    private CheckDaoIF checkDaoIF;

    @Override
    public ArrayList<CheckDto> findListCheck(int id) {
        ArrayList<Checkin> list = checkDaoIF.findListCheck(id);
        return CheckMapper.toListDto(list);
    }

    @Transactional
    @Override
    public ArrayList<CheckDto> create(ArrayList<RequestJsonClient> data) {
        Date now = new Date();
        Timestamp ts = new Timestamp(now.getTime());
        ArrayList<Checkin> list = new ArrayList<>();
        for (RequestJsonClient requestJsonClient : data) {
            System.out.println("newCheck: "+requestJsonClient.getId());
            System.out.println("newCheck: "+requestJsonClient.getStatus());
            Checkin check = new Checkin();
            check.setIdSubTeaStu(requestJsonClient.getId());
            check.setStatus(requestJsonClient.getStatus());
            check.setTime(ts);
            list.add(check);
        }
        ArrayList<Checkin> newCheck = (ArrayList) checkDaoIF.saveAll(list);
        return CheckMapper.toListDto(newCheck);
    }

}
