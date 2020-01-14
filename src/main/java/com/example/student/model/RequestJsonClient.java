/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.student.model;

import java.util.List;

/**
 *
 * @author MinhKudo
 */
public class RequestJsonClient {



        private int id;
        private String codeSub;
        private String codeTeach;
        private String codeStudent;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCodeSub() {
            return codeSub;
        }

        public void setCodeSub(String codeSub) {
            this.codeSub = codeSub;
        }

        public String getCodeTeach() {
            return codeTeach;
        }

        public void setCodeTeach(String codeTeach) {
            this.codeTeach = codeTeach;
        }

        public String getCodeStudent() {
            return codeStudent;
        }

        public void setCodeStudent(String codeStudent) {
            this.codeStudent = codeStudent;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

}
