package com.example.wineapi.data.repository.wine;

import com.example.wineapi.data.entity.wine.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class WineRepository {
    private final EntityManager em;

    @Autowired
    public WineRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<Wine> findById(Long id) {
        return em.createQuery("select m from Wine m where m.id = :id", Wine.class)
                .setParameter("id", id)
                .getResultList()
                .stream().findAny();
    }
}
