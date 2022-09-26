package com.example.wineapi.data.repository;

import com.example.wineapi.data.domain.Question;
import com.example.wineapi.data.domain.QuestionOption;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionRepository {
    private final EntityManager em;


    public QuestionRepository(EntityManager em) {
        this.em = em;
    }

    // id를 parameter로 database에 저장된 question 검색 (데이터베이스 인덱스 기법 미적용)
    public Optional<Question> findById(Integer id) {
        return em.createQuery("select m from Question m where m.id = :id", Question.class)
                .setParameter("id", id)
                .getResultList()
                .stream().findAny();
    }

    public ArrayList<Question> findByCategory(String category) {
        ArrayList<Question> result = new ArrayList<>();
        result.addAll(em.createQuery("select m from Question m where m.category = :category", Question.class)
                .setParameter("category", category)
                .getResultList());
        return result;
    }
    public List<QuestionOption> findByQuestionOption(Integer questionIndex) {
        return em.createQuery("select m from QuestionOption m where m.questionIndex = :questionIndex", QuestionOption.class)
                .setParameter("questionIndex", questionIndex)
                .getResultList();
    }

}
