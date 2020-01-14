/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.controller;

import com.example.student.config.MyConfig;
import com.example.student.dto.CheckDto;
import com.example.student.dto.SubTeachDto;
import com.example.student.dto.SubTeachStudentDto;
import com.example.student.model.AngularModel;
import com.example.student.model.RequestJsonClient;
import com.example.student.model.Result;
import com.example.student.service.CheckServiceIF;
import com.example.student.service.SubTeachServiceIF;
import com.example.student.service.SubTeachStudentServiceIF;
import com.example.student.util.RequestTool;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MinhKudo
 */
@Controller
@RequestMapping(value = "/page-teach")
public class PageTeachController {

    @Autowired
    private SubTeachServiceIF subTeachService;

    @Autowired
    private SubTeachStudentServiceIF subTeachStudentSerivce;

    @Autowired
    private CheckServiceIF checkService;

    @GetMapping(value = "/index")
    public String getIndexTeach() {

        return "pageTeach/index";
    }

    @GetMapping(value = {"/list"})
    public String list(Model model) {
//       
        return "teach/list";
    }

    @PostMapping(value = {"/list"})
    @ResponseBody
    public ResponseEntity<AngularModel<SubTeachDto>> listSubjectTeach(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        System.out.println("codeSub: " + codeSub);
        System.out.println("maxRow: " + maxRow);
        System.out.println("crPage: " + crPage);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Đây là lấy ra username là email
        String codeTeach = (String) auth.getPrincipal();
        System.out.println("codeTeach: " + codeTeach);
        ArrayList<SubTeachDto> subTeach = subTeachService.list(crPage, maxRow, codeSub, codeTeach);
        int count = subTeach.size();
        System.out.println("count: " + count);
        AngularModel<SubTeachDto> ngModel = new AngularModel<>();
        ngModel.setListObject(subTeach);
        ngModel.setTotalRow(count);
        if (subTeach == null || subTeach.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @GetMapping(value = "/check")
    public String getCheck(Model model, @RequestParam(value = "codeSub") String codeSub) {
        model.addAttribute("codeSub", codeSub);
        return "teach/check";
    }

    @PostMapping(value = {"/check"})
    @ResponseBody
    public ResponseEntity<AngularModel<SubTeachStudentDto>> postCheck(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String codeTeach = (String) auth.getPrincipal();

        ArrayList<SubTeachStudentDto> subTeachStu = subTeachStudentSerivce.list(crPage, maxRow, codeSub, codeTeach, null);
        int count = subTeachStu.size();
        AngularModel<SubTeachStudentDto> ngModel = new AngularModel<>();
        ngModel.setListObject(subTeachStu);
        ngModel.setTotalRow(count);
        if (subTeachStu == null || subTeachStu.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = {"/check"})
    @ResponseBody
    public ResponseEntity<Result> putCheck(Model model, @RequestBody ArrayList<RequestJsonClient> data) {
        Result rs = null;
        System.out.println("data: "+data.get(0).getId());
        System.out.println("data: "+data.get(0).getStatus());
        if (checkService.create(data) == null) {
            rs = new Result(Result.SUCCESS, "Điểm Danh Thất Bại");
        } else {
            rs = new Result(Result.SUCCESS, "Điểm Danh Thành Công");
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}
