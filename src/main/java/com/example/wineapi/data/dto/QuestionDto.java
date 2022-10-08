package com.example.wineapi.data.dto;

import com.example.wineapi.data.domain.Question;

import java.util.ArrayList;

public class QuestionDto {
    private Integer id;
    private Integer index; // 질문 식별 번호
    private String category; // 질문 대분류
    private String answerFormat;   // 질문 형식
    private String context;  // 질문 형태
    private ArrayList<String> question_option;


    public QuestionDto(Question question) {
        this.id = question.getId();
        this.index = question.getIndex();
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAnswerFormat() {
        return answerFormat;
    }

    public void setAnswerFormat(String answerFormat) {
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
