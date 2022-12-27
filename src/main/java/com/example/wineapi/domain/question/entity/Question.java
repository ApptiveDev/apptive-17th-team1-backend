package com.example.wineapi.domain.question.entity;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer category; // 질문 대분류
    private Integer answerFormat;   // 질문 형식
    private String context;  // 질문 형태

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getAnswerFormat() {
        return answerFormat;
    }

    public void setAnswerFormat(Integer answerFormat) {
        this.answerFormat = answerFormat;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
