package com.example.wineapi.controller;

import com.example.wineapi.data.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.wineapi.service.QuestionService;

import java.util.ArrayList;

@RestController
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/questionn/{id}", method = RequestMethod.GET)
    public ResponseEntity<QuestionDto> QuestionApi(@PathVariable Integer id) {

        QuestionDto result = questionService.JsonQuestionById(id);

//        // null 값일때 404반환
//        if (result.getId() == null) {
//            return ResponseEntity.notFound().build();
//        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/question/category/{category}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<QuestionDto>> QuestionByCategory(@PathVariable("category") Integer category) {
        ArrayList<QuestionDto> result = questionService.QuestionDtoByCategory(category);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
