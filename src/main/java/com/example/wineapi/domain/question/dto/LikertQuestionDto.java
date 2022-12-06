package com.example.wineapi.domain.question.dto;

import com.example.wineapi.domain.question.entity.Question;

import java.util.ArrayList;

public class LikertQuestionDto extends QuestionDto {

    private ArrayList<String> scaleList;
    public LikertQuestionDto(Question question) {
        super(question);
        this.scaleList = new ArrayList<>();
    }

    public ArrayList<String> getScaleList() {
        return scaleList;
    }

    public void setScaleList(ArrayList<String> scaleList) {
        this.scaleList = scaleList;
    }
}