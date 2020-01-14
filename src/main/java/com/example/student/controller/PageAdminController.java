/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.controller;

import com.example.student.config.MyConfig;
import com.example.student.dto.StudentDto;
import com.example.student.dto.SubTeachDto;
import com.example.student.dto.SubjectDto;
import com.example.student.dto.TeachDto;
import com.example.student.model.AngularModel;
import com.example.student.model.Result;
import com.example.student.service.StudentServiceIF;
import com.example.student.service.SubTeachServiceIF;
import com.example.student.service.SubjectServiceIF;
import com.example.student.service.TeachServiceIF;
import com.example.student.util.RequestTool;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author MinhKudo
 */
@Controller
@RequestMapping(value = "/page-admin")
public class PageAdminController {

    @Autowired
    private TeachServiceIF teachService;

    @Autowired
    private StudentServiceIF studentService;

    @Autowired
    private SubTeachServiceIF subTeachSerivce;

    @Autowired
    private SubjectServiceIF subjectService;

    @GetMapping(value = "/index")
    public String getIndexAdmin() {
        return "pageAdmin/index";
    }

    //Giao Vien (Teach) start
    @GetMapping(value = {"/teach", "/teach/view"})
    public String listTeach(Model model) {
        return "teach/view";
    }

    @PostMapping(value = {"/teach", "/teach/view"})
    @ResponseBody
    public ResponseEntity<AngularModel<TeachDto>> listDataTeach(Model model, HttpServletRequest request) {
        String code = RequestTool.getString(request, "code");
        String name = RequestTool.getString(request, "name");
        int status = RequestTool.getInt(request, "status", -1);
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        ArrayList<TeachDto> teach = teachService.list(crPage, maxRow, code, name, status);
        int count = teach.size();
        AngularModel<TeachDto> ngModel = new AngularModel<>();
        ngModel.setListObject(teach);
        ngModel.setTotalRow(count);
        if (teach == null || teach.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @GetMapping(value = "/teach/add")
    public String addGetTeach(Model model) {
        return "teach/add";
    }

    @PostMapping(value = "/teach/add")
    @ResponseBody
    public ResponseEntity<Result> addPostTeach(Model model, @RequestBody @Valid TeachDto teachForm) {
        if (teachService.create(teachForm) != null) {
            return new ResponseEntity<>(new Result(Result.SUCCESS, "Thêm Thành Công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Result(Result.ERROR, "Thêm Thất Bại"), HttpStatus.OK);
        }

    }

    @GetMapping(value = "/teach/edit")
    public String editGetTeach(Model model, @RequestParam(value = "id") int id) {
        model.addAttribute("id", id);
        return "teach/edit";
    }

    @PostMapping(value = "/teach/edit")
    @ResponseBody
    public TeachDto editDataPostTeach(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        return teachService.find(id);
    }

    @PutMapping(value = "/teach/edit")
    @ResponseBody
    public ResponseEntity<Result> editPostTeach(Model model, @RequestBody TeachDto teachForm) {
        if (teachService.update(teachForm) != null) {
            return new ResponseEntity<>(new Result(Result.SUCCESS, "Sửa Thành Công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Result(Result.ERROR, "Sửa Thất Bại"), HttpStatus.OK);
        }
//        }
    }

    @DeleteMapping(value = "/teach/delete")
    public ResponseEntity<Model> deletePostTeach(Model model, HttpServletRequest request, @RequestParam(value = "id") int id, RedirectAttributes rdrAtt) {
        System.out.println("id: " + id);
        if (teachService.delete(id) != null) {
            model.addAttribute("messing", "Xóa Thành Công");
            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            model.addAttribute("messing", "Xóa Thất Bại");
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
    }
    //Giao Vien (Teach) end

    //Hoc Sinh (Student) start
    @GetMapping(value = {"/student/", "/student/view"})
    public String listStudent(Model model) {
        return "student/view";
    }

    @PostMapping(value = {"/student/", "/student/view"})
    @ResponseBody
    public ResponseEntity<AngularModel<StudentDto>> listDataStudent(Model model, HttpServletRequest request) {
        String code = RequestTool.getString(request, "code");
        String name = RequestTool.getString(request, "name");
        int status = RequestTool.getInt(request, "status", -1);
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        System.out.println("code: " + code);
        System.out.println("name: " + name);
        System.out.println("status: " + status);
        System.out.println("maxRow: " + maxRow);
        System.out.println("crPage: " + crPage);
        ArrayList<StudentDto> student = studentService.list(crPage, maxRow, code, name, status);
        int count = student.size();
        AngularModel<StudentDto> ngModel = new AngularModel<>();
        ngModel.setListObject(student);
        ngModel.setTotalRow(count);
        System.out.println("count: " + count);
        if (student == null || student.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @GetMapping(value = "/student/add")
    public String addGetStudent(Model model) {
        return "student/add";
    }

    @PostMapping(value = "/student/add")
    public ResponseEntity<Result> addPostStudent(Model model, @RequestBody @Valid StudentDto studentForm) {
        if (studentService.create(studentForm) != null) {
            return new ResponseEntity<>(new Result(Result.SUCCESS, "Thêm Thành Công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Result(Result.ERROR, "Thêm Thất Bại"), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/student/edit")
    public String editGetStudent(Model model, @RequestParam(value = "id") int id) {
        model.addAttribute("id", id);
        return "student/edit";
    }

    @PostMapping(value = "/student/edit")
    @ResponseBody
    public StudentDto editDataPostStudent(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        return studentService.find(id);
    }

    @PutMapping(value = "/student/edit")
    @ResponseBody
    public ResponseEntity<Result> editPostStudent(Model model, @RequestBody StudentDto studentForm) {
        System.out.println("student: " + studentForm);
        if (studentService.update(studentForm) != null) {
            return new ResponseEntity<>(new Result(Result.SUCCESS, "Thêm Thành Công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Result(Result.ERROR, "Thêm Thất Bại"), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/student/delete")
    public ResponseEntity<Model> deletePostStudent(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        if (studentService.delete(id) != null) {
            model.addAttribute("messing", "Xóa Thành Công");
            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            model.addAttribute("messing", "Xóa Thất Bại");
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
    }
    //Hoc Sinh (Student) end

    //Môn Học (Subject) start
    @GetMapping(value = {"/subject/", "/subject/view"})
    public String listSubject(Model model) {
        return "subject/view";
    }

    @PostMapping(value = {"/subject/", "/subject/view"})
    @ResponseBody
    public ResponseEntity<AngularModel<SubjectDto>> listDataSubject(Model model, HttpServletRequest request) {
        String code = RequestTool.getString(request, "code");
        String name = RequestTool.getString(request, "name");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);

        ArrayList<SubjectDto> subject = subjectService.list(crPage, maxRow, code, name);
        int count = subject.size();
        AngularModel<SubjectDto> ngModel = new AngularModel<>();
        ngModel.setListObject(subject);
        ngModel.setTotalRow(count);
        if (subject == null || subject.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @GetMapping(value = "/subject/add")
    public String addGetSubject(Model model) {
        return "subject/add";
    }

    @PostMapping(value = "/subject/add")
    @ResponseBody
    public ResponseEntity<Result> addPostSubject(Model model, @RequestBody
            @Valid SubjectDto subjectForm) {
        if (subjectService.create(subjectForm) != null) {
            return new ResponseEntity<>(new Result(Result.SUCCESS, "Thêm Thành Công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Result(Result.ERROR, "Thêm Thất Bại"), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/subject/edit")
    public String editGetSubject(Model model, @RequestParam(value = "id") int id) {
        model.addAttribute("id", id);
        return "subject/edit";
    }

    @PostMapping(value = "/subject/edit")
    @ResponseBody
    public SubjectDto editDataPostSubject(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        return subjectService.find(id);
    }

    @PutMapping(value = "/subject/edit")
    @ResponseBody
    public ResponseEntity<Result> editPostSubject(Model model, @RequestBody SubjectDto subjectForm) {
        System.out.println("subject: " + subjectForm);
        if (subjectService.update(subjectForm) != null) {
            return new ResponseEntity<>(new Result(Result.SUCCESS, "Sửa Thành Công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Result(Result.ERROR, "Sửa Thất Bại"), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/subject/delete")
    public ResponseEntity<Model> deletePostSubject(Model model, HttpServletRequest request, @RequestParam(value = "id") int id, RedirectAttributes rdrAtt) {
        if (subjectService.delete(id) != null) {
            model.addAttribute("messing", "Xóa Thành Công");
            return new ResponseEntity<>(model, HttpStatus.OK);
        } else {
            model.addAttribute("messing", "Xóa Thất Bại");
            return new ResponseEntity<>(model, HttpStatus.OK);
        }

    }
    //Môn Học (Subject) end

    //Môn + Giáo Viên (Sub + teach) start
    @GetMapping(value = {"/sub-teach/", "/sub-teach/view"})
    public String listSubTeach(Model model) {
        return "subTeach/view";
    }

    @PostMapping(value = {"/sub-teach/", "/sub-teach/view"})
    @ResponseBody
    public ResponseEntity<AngularModel<SubTeachDto>> listDataSubTeach(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        ArrayList<SubTeachDto> subTeach = subTeachSerivce.list(crPage, maxRow, codeSub, codeTeach);
        int count = subTeach.size();
        System.out.println("count: " + count);
        AngularModel<SubTeachDto> ngModel = new AngularModel<>();
        ngModel.setListObject(subTeach);
        ngModel.setTotalRow(count);
        System.out.println("count: " + count);
        if (subTeach == null || subTeach.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @GetMapping(value = "/sub-teach/edit")
    public String editGetSubTeach(Model model, @RequestParam(value = "id") int id) {
        model.addAttribute("id", id);
        return "subTeach/edit";
    }

    @PutMapping(value = "/sub-teach/edit")
    @ResponseBody
    public SubTeachDto editDataPostSubTeach(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        return subTeachSerivce.find(id);
    }

    @PostMapping(value = "/sub-teach/edit")
    @ResponseBody
    public ResponseEntity<Result> editPostSubTeach(Model model, @RequestBody SubTeachDto subTeach) {
        if (subTeachSerivce.update(subTeach) != null) {
            return new ResponseEntity<>(new Result(Result.SUCCESS, "Sửa Thành Công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Result(Result.ERROR, "Sửa Thất Bại"), HttpStatus.OK);
        }
    }
    //Môn + Giáo Viên (Sub + teach) end
}
