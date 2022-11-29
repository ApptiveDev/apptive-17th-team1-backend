package com.example.wineapi.global.config;

import com.example.wineapi.domain.question.repository.QuestionRepository;
import com.example.wineapi.domain.question.service.QuestionService;
import com.example.wineapi.domain.wine.repository.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {
    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public QuestionService memberService() {
        return new QuestionService(questionRepository(), wineRepository());
    }

    @Bean
    public QuestionRepository questionRepository() {
        return new QuestionRepository(em);
    }

    @Bean
    public WineRepository wineRepository() {
        return new WineRepository(em);
    }

}
