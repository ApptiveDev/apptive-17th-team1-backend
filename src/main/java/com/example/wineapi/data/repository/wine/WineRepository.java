package com.example.wineapi.data.repository.wine;

import com.example.wineapi.data.entity.question.Question;
import com.example.wineapi.data.entity.wine.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class WineRepository {
    private final EntityManager em;

    @Autowired
    public WineRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<Wine> findWineById(Long id) {
        return em.createQuery("select m from Wine m where m.id = :id", Wine.class)
                .setParameter("id", id)
                .getResultList()
                .stream().findAny();
    }

    public ArrayList<Wine> allWineList() {
        ArrayList<Wine> result = new ArrayList<>();
        result.addAll(em.createQuery("select m from Wine m", Wine.class)
                .getResultList());
        return result;
    }
}
