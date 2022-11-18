package com.example.wineapi.data.repository.wine;

import com.example.wineapi.data.entity.wine.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
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

    public List<Wine> wineListByQuery(String query) {
        return em.createQuery(query, Wine.class).getResultList();
    }
}
