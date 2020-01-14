/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.service;

import com.example.student.dto.CheckDto;
import com.example.student.model.RequestJsonClient;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinhKudo
 */
@Service
public interface CheckServiceIF {

    public ArrayList<CheckDto> findListCheck(int id);

    public ArrayList<CheckDto> create(ArrayList<RequestJsonClient> data);
}
