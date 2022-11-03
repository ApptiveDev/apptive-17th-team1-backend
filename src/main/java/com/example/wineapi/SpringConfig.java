package com.example.wineapi;

import com.example.wineapi.data.repository.QuestionRepository;
import com.example.wineapi.service.QuestionService;
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
        return new QuestionService(questionRepository());
    }

    @Bean
    public QuestionRepository questionRepository() {
        return new QuestionRepository(em);
    }
}
