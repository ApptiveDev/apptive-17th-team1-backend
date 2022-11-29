package com.example.wineapi.domain.member.dto;

public class TokenDTO {
    private String email;
    private String pass;

    private String name;

    private Long gender;

    private Long age;

    private String token;

    public TokenDTO(String email, String pass, String name, Long gender, Long age, String token) {
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.token = token;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenDTO{" +
                "email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", token='" + token + '\'' +
                '}';
    }
}

