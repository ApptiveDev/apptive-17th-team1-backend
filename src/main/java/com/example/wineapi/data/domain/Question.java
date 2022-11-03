package com.example.wineapi.data.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int question_index; // 질문 식별 번호
    private String category; // 질문 대분류
    private String answerFormat;   // 질문 형식
    private String context;  // 질문 형태

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndex() {
        return question_index;
    }

    public void setIndex(Integer index) {
        this.question_index = index;
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
}
