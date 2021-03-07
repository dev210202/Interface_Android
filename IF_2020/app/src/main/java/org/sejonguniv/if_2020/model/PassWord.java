package org.sejonguniv.if_2020.model;

public class PassWord {
    String pw;
    String role;

    public PassWord(String pw, String role) {
        this.pw = pw;
        this.role = role;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
