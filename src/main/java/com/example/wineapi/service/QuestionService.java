package com.example.wineapi.service;

import com.example.wineapi.data.entity.question.Question;
import com.example.wineapi.data.entity.question.QuestionOption;
import com.example.wineapi.data.dto.QuestionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.wineapi.data.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Transactional
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuestionDto JsonQuestionById(Integer id) {

        // db에서 id값으로 question 찾기
        QuestionDto question = new QuestionDto(questionRepository.findById(id).orElseGet(() -> new Question()));

        // question id가 null 값일때
        if (question.getId() == null) {
            return null;
        }

        // question obj to hashmap (column 중 option에 저장된 string을 list로 변환하기 위함)
        ObjectMapper objectMapper = new ObjectMapper();
        LinkedHashMap<String, Object> map = objectMapper.convertValue(question, LinkedHashMap.class);

        // question의 종류에 따른 option 반환여부 결정
        if (question.getAnswerFormat() == 1) {
            List<QuestionOption> qoList = questionRepository.findByQuestionOption(question.getId());
            ArrayList<String> optionList = new ArrayList<>();
            for (QuestionOption option : qoList) {
                question.getQuestion_option().add(option.getChoice());
            }
            map.remove("option");
            map.put("option", optionList);
        } else {
            map.remove("option");
        }

        // hashmap to json string
        Gson gson = new Gson();
        String result = gson.toJson(map);
        return question;
    }

    public ArrayList<QuestionDto> QuestionDtoByCategory(Integer category) {
        ArrayList<Question> questionArrayList = questionRepository.findByCategory(category);
        ArrayList<QuestionDto> questionDtoArrayList = new ArrayList<>();
        for (int i = 0; i < questionArrayList.size(); i++) {
            QuestionDto questionDto = new QuestionDto(questionArrayList.get(i));

            // question의 종류에 따른 option 반환여부 결정
            if (questionDto.getAnswerFormat().equals(1)) {
                List<QuestionOption> qoList = questionRepository.findByQuestionOption(questionDto.getId());
                ArrayList<QuestionOption> optionList = new ArrayList<>();
                optionList.addAll(qoList);
                System.out.println(optionList.size());
                for (int j = 0; j < optionList.size(); j++) {
                    questionDto.getQuestion_option().add(optionList.get(j).getChoice());
                }
            }
            questionDtoArrayList.add(questionDto);
        }

        return questionDtoArrayList;
    }
}
