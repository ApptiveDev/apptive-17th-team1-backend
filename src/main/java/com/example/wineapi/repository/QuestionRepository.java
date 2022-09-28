package com.example.wineapi.repository;

import com.example.wineapi.domain.Question;

import javax.persistence.EntityManager;
import java.util.Optional;

public class QuestionRepository {
    private final EntityManager em;


    public QuestionRepository(EntityManager em) {
        this.em = em;
    }

    // id를 parameter로 database에 저장된 question 검색 (데이터베이스 인덱스 기법 미적용)
    public Optional<Question> findById(Long id) {
        return em.createQuery("select m from Question m where m.id = :id", Question.class)
                .setParameter("id", id)
                .getResultList()
                .stream().findAny();
    }
}
