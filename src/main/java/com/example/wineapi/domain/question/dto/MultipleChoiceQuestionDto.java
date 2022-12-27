package com.example.wineapi.domain.question.dto;

import com.example.wineapi.domain.question.entity.Question;

import java.util.ArrayList;

public class MultipleChoiceQuestionDto extends QuestionDto {
    private ArrayList<String> question_option;

    public MultipleChoiceQuestionDto(Question question) {
        super(question);
        this.question_option = new ArrayList<>();
    }

    public ArrayList<String> getQuestion_option() {
        return question_option;
    }

    public void setQuestion_option(ArrayList<String> question_option) {
        this.question_option = question_option;
    }

    public void addOption(String option) {
        this.question_option.add(option);
    }
}
