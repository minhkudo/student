/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.util;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author TUANPLA
 */
public class RequestTool {

    public static void debugParam(HttpServletRequest request) {
        Enumeration<String> allParam = request.getParameterNames();
        while (allParam.hasMoreElements()) {
            String oneParam = allParam.nextElement();
        }
    }

    public static int getInt(HttpServletRequest request, String param) {
        int tem;
        try {
            tem = Integer.parseInt(request.getParameter(param).trim());
        } catch (Exception e) {
            tem = 0;
        }
        return tem;
    }

    public static boolean getBoolean(HttpServletRequest request, String param) {
        boolean tem;
        try {
            String str = request.getParameter(param).trim();
            tem = str != null && (str.equals("1") || str.equals("true"));
        } catch (Exception e) {
            tem = false;
        }
        return tem;
    }

    public static int getInt(HttpServletRequest request, String param, int defaultVal) {
        int tem;
        try {
            tem = Integer.parseInt(request.getParameter(param).trim());
        } catch (Exception e) {
            tem = defaultVal;
        }
        return tem;
    }

    public static long getLong(HttpServletRequest request, String param) {
        long tem;
        try {
            tem = Long.parseLong(request.getParameter(param).trim());
        } catch (Exception e) {
            tem = 0;
        }
        return tem;
    }

    public static double getDouble(HttpServletRequest request, String param) {
        double tem;
        try {
            tem = Double.parseDouble(request.getParameter(param).trim());
        } catch (Exception e) {
            tem = 0;
        }
        return tem;
    }

    public static String getString(HttpServletRequest request, String param) {
        String str;
        try {
            str = request.getParameter(param).trim();
        } catch (Exception e) {
            str = "";
        }
        return str;
    }

    public static String getString(HttpServletRequest request, String param, String defaultVal) {
        String str;
        try {
            str = request.getParameter(param).trim();
        } catch (Exception e) {
            str = defaultVal;
        }
        return str;
    }

    public static Float getFloat(HttpServletRequest request, String param) {
        float tem;
        try {
            tem = Float.parseFloat(request.getParameter(param).trim());
        } catch (Exception e) {
            tem = 0;
        }
        return tem;
    }
}
