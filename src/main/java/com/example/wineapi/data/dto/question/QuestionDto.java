package com.example.wineapi.data.dto.question;

import com.example.wineapi.data.entity.question.Question;

import java.util.ArrayList;

public class QuestionDto {
    private Integer id;
    private Integer category; // 질문 대분류
    private Integer answerFormat;   // 질문 형식
    private String context;  // 질문 형태
    private ArrayList<String> question_option;


    public QuestionDto(Question question) {
        this.id = question.getId();
        this.category = question.getCategory();
        this.answerFormat = question.getAnswerFormat();
        this.context = question.getContext();
        this.question_option = new ArrayList<>();
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

    public ArrayList<String> getQuestion_option() {
        return question_option;
    }

    public void setQuestion_option(ArrayList<String> question_option) {
        this.question_option = question_option;
    }
}
