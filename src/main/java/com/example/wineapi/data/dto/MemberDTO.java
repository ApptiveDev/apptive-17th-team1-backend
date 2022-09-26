package com.example.wineapi.data.dto;

public class MemberDTO {
    private String email;
    private String pass;

    public MemberDTO(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
