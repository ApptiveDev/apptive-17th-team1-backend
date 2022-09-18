package com.example.wineapi.service;

import com.example.wineapi.domain.Question;
import org.springframework.transaction.annotation.Transactional;
import com.example.wineapi.repository.QuestionRepository;

import java.util.Optional;

@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Optional<Question> findOne(Long id) {
        return questionRepository.findById(id);
    }
}
