package com.example.wineapi.domain.question.dto;

import com.example.wineapi.domain.question.entity.Question;

public class QuestionDto {
    private Integer id;
    private Integer category; // 질문 대분류
    private Integer answerFormat;   // 질문 형식
    private String context;  // 질문 형태

    public QuestionDto(Question question) {
        this.id = question.getId();
        this.category = question.getCategory();
        this.answerFormat = question.getAnswerFormat();
        this.context = question.getContext();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
