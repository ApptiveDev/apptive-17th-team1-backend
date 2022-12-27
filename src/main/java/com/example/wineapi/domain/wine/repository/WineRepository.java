package com.example.wineapi.domain.wine.repository;

import com.example.wineapi.domain.wine.entity.Wine;
import com.example.wineapi.domain.wine.entity.wineProperty.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
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

    public List<Wine> wineListByQuery(String query) {
        return em.createQuery(query, Wine.class).getResultList();
    }

    public Optional<Alcohol> selectAlcohol(Integer id) {
        return em.createQuery("select a from Alcohol a where a.id = :id", Alcohol.class)
                .setParameter("id", id)
                .getResultList().stream().findAny();
    }

    public Optional<Flavor> selectFlavor(Integer id) {
        return em.createQuery("select a from Flavor a where a.id = :id", Flavor.class)
                .setParameter("id", id)
                .getResultList().stream().findAny();
    }

    public Optional<Food> selectFood(Integer id) {
        return em.createQuery("select a from Food a where a.id = :id", Food.class)
                .setParameter("id", id)
                .getResultList().stream().findAny();
    }

    public Optional<Country> selectCountry(Integer id) {
        return em.createQuery("select a from Country a where a.id = :id", Country.class)
                .setParameter("id", id)
                .getResultList().stream().findAny();
    }

    public Optional<Variety> selectVariety(Integer id) {
        return em.createQuery("select a from Variety a where a.id = :id", Variety.class)
                .setParameter("id", id)
                .getResultList().stream().findAny();
    }

    public Optional<Type> selectType(Integer id) {
        return em.createQuery("select a from Type a where a.id = :id", Type.class)
                .setParameter("id", id)
                .getResultList().stream().findAny();
    }
}
