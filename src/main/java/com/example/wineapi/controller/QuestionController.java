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

        String result = questionService.JsonQuestionById(id);

        // null 값일때 404반환
        if (result == "null") {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
