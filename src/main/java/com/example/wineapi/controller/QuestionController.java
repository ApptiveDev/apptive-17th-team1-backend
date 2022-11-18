package com.example.wineapi.controller;

import com.example.wineapi.data.dto.question.AnswerDto;
import com.example.wineapi.data.dto.question.QuestionDto;
import com.example.wineapi.data.dto.wine.WineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.wineapi.service.QuestionService;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<QuestionDto>> QuestionByCategory(@PathVariable("category") Integer category) {
        if (category > 3) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        ArrayList<QuestionDto> result = questionService.QuestionDtoByCategory(category);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/answer")
    public ResponseEntity<WineDto> recommendWine(@RequestBody AnswerDto answerDto) {
        WineDto result = questionService.findSimilarWineDto(answerDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
