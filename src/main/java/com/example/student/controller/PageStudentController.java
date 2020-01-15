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
import com.example.student.model.Result;
import com.example.student.service.CheckServiceIF;
import com.example.student.service.SubTeachServiceIF;
import com.example.student.service.SubTeachStudentServiceIF;
import com.example.student.util.RequestTool;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MinhKudo
 */
@Controller
@RequestMapping(value = "/page-student")
public class PageStudentController {

    @Autowired
    private SubTeachServiceIF subTeachService;

    @Autowired
    private SubTeachStudentServiceIF subTeachStudentSerivce;

    @Autowired
    private CheckServiceIF checkService;

    @GetMapping(value = "/index")
    public String getIndexStudent(Model model, HttpSession session) {
        return "pageStudent/index";
    }

    @GetMapping(value = "/danh-sach-dang-ky")
    public String getReigStudent(Model model, HttpSession session) {
        return "student/DoStuTeaSub";
    }

    @PostMapping(value = {"/danh-sach-dang-ky"})
    @ResponseBody
    public ResponseEntity<AngularModel<SubTeachDto>> listDataDoReig(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int status = RequestTool.getInt(request, "status", -1);
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        // Ví dụ lấy thông tin người thực hiện tác vụ createUser từ trong SecurityContext
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Đây là lấy ra username là email
        String codeStudent = (String) auth.getPrincipal();
        // Để lấy ra các thông tin khác vd như userId thì yêu cầu phải lưu trong thông tin vào object
        // và object được lưu trong Credentials. Gọi hàm getCredentials() để lấy ra object rồi tiếp tục lấy ra các property khác
        // auth.getCredentials()
        // Đây là lấy ra username là email
        ArrayList<SubTeachDto> sub_teach = subTeachService.listNotReig(crPage, maxRow, codeSub, codeTeach, codeStudent);
        int count = sub_teach.size();
        AngularModel<SubTeachDto> ngModel = new AngularModel<>();
        ngModel.setListObject(sub_teach);
        ngModel.setTotalRow(count);
        if (sub_teach == null || sub_teach.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @PostMapping(value = "/dang-ky")
    public ResponseEntity<Model> registerPostStudent(Model model,
            @RequestParam(value = "codeSub") String codeSub,
            @RequestParam(value = "codeTeach") String codeTeach) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String codeStudent = (String) auth.getPrincipal();

        SubTeachStudentDto sts = new SubTeachStudentDto();
        sts.setCodeStudent(codeStudent);
        sts.setCodeSub(codeSub);
        sts.setCodeTeach(codeTeach);
        if (subTeachStudentSerivce.create(sts) != null) {
            model.addAttribute("messing", "Đăng Ký Thành Công");
            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            model.addAttribute("messing", "Đăng Ký Thất Bại");
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/danh-sach-chua-dang-ky")
    public String getNotReigStudent(Model model, HttpSession session) {

        return "student/NotStuTeaSub";
    }

    @PostMapping(value = {"/danh-sach-chua-dang-ky"})
    @ResponseBody
    public ResponseEntity<AngularModel<SubTeachDto>> listDataNotReig(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int status = RequestTool.getInt(request, "status", -1);
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String codeStudent = (String) auth.getPrincipal();

        ArrayList<SubTeachDto> sub_teach = subTeachService.listDoReig(crPage, maxRow, codeSub, codeTeach, codeStudent);
        int count = sub_teach.size();
        AngularModel<SubTeachDto> ngModel = new AngularModel<>();
        ngModel.setListObject(sub_teach);
        ngModel.setTotalRow(count);
        if (sub_teach == null || sub_teach.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @PostMapping(value = "/huy-dang-ky")
    public ResponseEntity<Model> registerNotPostStudent(Model model,
            @RequestParam(value = "codeSub") String codeSub,
            @RequestParam(value = "codeTeach") String codeTeach) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Đây là lấy ra username là email
        String codeStudent = (String) auth.getPrincipal();

        SubTeachStudentDto sts = new SubTeachStudentDto();
        sts.setCodeStudent(codeStudent);
        sts.setCodeSub(codeSub);
        sts.setCodeTeach(codeTeach);

        if (subTeachStudentSerivce.delete(sts) != null) {
            model.addAttribute("messing", "Hủy Mon Học Thành Công");
            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            model.addAttribute("messing", "Hủy Mon Học Thất Bại");
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/list")
    public String getListStudent(Model model, HttpSession session) {
        return "student/list";
    }

    @PostMapping(value = {"/list"})
    @ResponseBody
    public ResponseEntity<AngularModel<SubTeachStudentDto>> listSubjectTeach(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String codeStudent = (String) auth.getPrincipal();

        ArrayList<SubTeachStudentDto> subTeach = subTeachStudentSerivce.list(crPage, maxRow, codeSub, codeTeach, codeStudent);
        int count = subTeach.size();
        AngularModel<SubTeachStudentDto> ngModel = new AngularModel<>();
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
    public String getCheck(Model model, @RequestParam(value = "id_sts") int id_sts) {
         model.addAttribute("id_sts", id_sts);
        return "student/check";
    }

    @PostMapping(value = {"/check"})
    @ResponseBody
    public ResponseEntity<AngularModel<CheckDto>> postCheck(Model model, HttpServletRequest request) {
        int id_sts = RequestTool.getInt(request, "id_sts");
        ArrayList<CheckDto> check = checkService.findListCheck(id_sts);
        int count = check.size();
        AngularModel<CheckDto> ngModel = new AngularModel<>();
        ngModel.setListObject(check);
        ngModel.setTotalRow(count);
        if (check == null || check.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Chưa Điểm Danh"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }
}
