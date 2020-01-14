/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.util;

import com.example.student.config.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author MinhKudo
 */
public class JwtUltis {

    public static Claims verifyToken(HttpServletRequest request) {
        System.out.println("------------------ session id: " + request.getSession().getId());
        String token = (String) request.getSession().getAttribute("SESSION");
        if (token == null || !token.startsWith(SecurityConstants.PREFIX)) {
            System.out.println("-------------------- null");
            return null;
        }
        System.out.println("-------------------- token: "+ token);
        // Decode
        return Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token.replace(SecurityConstants.PREFIX, ""))
                .getBody();
    }

    public static String generateToken(String code, String role) {
        Claims claims = Jwts.claims().setSubject(code);
        List<String> roles = new ArrayList<>();
        roles.add(role);
        // Thông tin lưu trữ trong JWT dạng json key value
        // Muốn lưu thêm thông tin khác thì cứ put vào
        claims.put("roles", roles);
        claims.put("code", code);
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + SecurityConstants.EXPIRATION);
        // Encode
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
        return SecurityConstants.PREFIX + token;
    }
}
