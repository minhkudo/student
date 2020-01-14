/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.controller;

import com.example.student.model.Result;
import com.example.student.request.LoginRequest;
import com.example.student.service.StudentServiceIF;
import com.example.student.service.TeachServiceIF;
import com.example.student.util.JwtUltis;
import com.example.student.util.Tool;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MinhKudo
 */
@Controller
public class LoginController {

    @Autowired
    private StudentServiceIF studentServiceIF;

    @Autowired
    private TeachServiceIF teachServiceIF;

    @GetMapping(value = "/login-student")
    public String getLoginStudent() {
        return "loginStudent";
    }

    @PostMapping(value = "/login-student")
    @ResponseBody
    public ResponseEntity<Result> loginStudent(Model model,
            @RequestBody @Valid LoginRequest login,
            HttpServletRequest request) {
        String token = studentServiceIF.login(login);

        if (Tool.checkNull(token)) {
            return new ResponseEntity<>(new Result(Result.ERROR, "Đăng Nhập Thất Bại"), HttpStatus.OK);
        }
        System.out.println("token: " + token);
        request.getSession().setAttribute("SESSION", token);
        System.out.println("session: " + request.getSession().getAttribute("SESSION"));
        return new ResponseEntity<>(new Result(Result.SUCCESS, "Đăng Nhập Thành Công"), HttpStatus.OK);
    }

    @GetMapping(value = "/login-teach")
    public String getLoginTeach() {
        return "loginTeach";
    }

    @PostMapping(value = "/login-teach")
    @ResponseBody
    public ResponseEntity<Result> loginTeach(Model model,
            @RequestBody @Valid LoginRequest login,
            HttpServletRequest request) {
        String token = teachServiceIF.login(login);
        if (Tool.checkNull(token)) {
            return new ResponseEntity<>(new Result(Result.ERROR, "Đăng Nhập Thất Bại"), HttpStatus.OK);
        }
        System.out.println("token: " + token);
        request.getSession().setAttribute("SESSION", token);
        System.out.println("session: " + request.getSession().getAttribute("SESSION"));
        return new ResponseEntity<>(new Result(Result.SUCCESS, "Đăng Nhập Thành Công"), HttpStatus.OK);
    }

    @GetMapping(value = "/login-admin")
    public String getLoginAdmin() {
        return "loginAdmin";
    }

    @PostMapping(value = "/login-admin")
    @ResponseBody
    public ResponseEntity<Result> loginAdmin(Model model,
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "password", required = true) String password,
            HttpServletRequest request) {
        if (code.equals("1") && password.equals("1")) {
            String token = JwtUltis.generateToken("1", "ADMIN");
            if (Tool.checkNull(token)) {
                return new ResponseEntity<>(new Result(Result.ERROR, "Đăng Nhập Thất Bại"), HttpStatus.OK);
            }
            System.out.println("token: " + token);
            request.getSession().setAttribute("SESSION", token);
            System.out.println("session: " + request.getSession().getAttribute("SESSION"));
            return new ResponseEntity<>(new Result(Result.SUCCESS, "Đăng Nhập Thành Công"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result(Result.ERROR, "Đăng Nhập Thất Bại"), HttpStatus.OK);
    }

    @GetMapping(value = "/logout-admin")
    public String getIndexStudent(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login-admin";
    }

    @GetMapping(value = "/logout-teach")
    public String getIndexTeach(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login-teach";
    }

    @GetMapping(value = "/logout-student")
    public String getIndexAdmin(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login-student";
    }
}
