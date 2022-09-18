package com.example.wineapi.controller;

import com.example.wineapi.domain.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.wineapi.service.QuestionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> QuestionApi(@PathVariable Long id) {

        // db에서 id값으로 question 찾기
        Question question = questionService.findOne(id).orElseGet(() -> new Question());

        // question id가 null 값일때 404 에러 반환
        if (question.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        // question obj to hashmap (column 중 option에 저장된 string을 list로 변환하기 위함)
        ObjectMapper objectMapper = new ObjectMapper();
        LinkedHashMap<String, Object> map = objectMapper.convertValue(question, LinkedHashMap.class);

        // question의 종류에 따른 option 반환여부 결정
        if (question.getForm() == 2) {
            ArrayList<String> optionList = new ArrayList<>(Arrays.asList(question.getOption().orElse("").split(",")));
            map.remove("option");
            map.put("option", optionList);
        } else {
            map.remove("option");
        }

        // hashmap to json string
        Gson gson = new Gson();
        String result = gson.toJson(map);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
