package com.example.wineapi.service;

import com.example.wineapi.domain.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.wineapi.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Optional;

@Transactional
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public String JsonQuestionById(Long id) {

        // db에서 id값으로 question 찾기
        Question question = questionRepository.findById(id).orElseGet(() -> new Question());

        // question id가 null 값일때
        if (question.getId() == null) {
            return "null";
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
        return result;
    }
}
