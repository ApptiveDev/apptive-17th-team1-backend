package com.example.wineapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

@Entity
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String context; // 질문 내용
    private Integer form;   // 질문 형식
    private String option;  // 질문 형태


    // 이하 getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getForm() {
        return form;
    }

    public void setForm(Integer form) {
        this.form = form;
    }

    public Optional<String> getOption() {
        return Optional.ofNullable(this.option);
    }

    public void setOption(String option) {
        this.option = option;
    }
}
