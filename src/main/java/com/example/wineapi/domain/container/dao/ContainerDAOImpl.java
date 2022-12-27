package com.example.wineapi.domain.container.dao;

import com.example.wineapi.domain.container.entity.Container;
import com.example.wineapi.domain.container.repository.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class ContainerDAOImpl implements ContainerDAO {
    private final ContainerRepository containerRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public ContainerDAOImpl (ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    @Override
    public Container insertContainer(Container container) {
        Container savedContainer = containerRepository.save(container);

        return savedContainer;
    }

    @Override
    public Container selectContainer(Long id) {
        Container selectContainer = containerRepository.getById(id);

        return selectContainer;
    }

    @Override
    public Optional<Container> selectContainer(Long user_id, Long wine_id) {
        return em.createQuery("select c from Container c where c.user_id = :user_id and c.wine_id = :wine_id", Container.class)
                .setParameter("user_id", user_id)
                .setParameter("wine_id", wine_id)
                .getResultList().stream().findAny();
    }

    @Override
    public List<Container> selectMyContainers(Long user_id) {
        List<Container> li = em.createQuery("select c from Container c where c.user_id =: user_id", Container.class)
                .setParameter("user_id", user_id)
                .getResultList();
        return li;
    }

    @Override
    public void deleteContainer(Long user_id, Long wine_id) {
        List<Container> li = em.createQuery("select c from Container c where c.user_id =: user_id and c.wine_id =: wine_id", Container.class)
                .setParameter("user_id", user_id)
                .setParameter("wine_id", wine_id)
                .getResultList();
        if(li.size() != 0) {
            containerRepository.delete(li.get(0));
        }
    }

    @Override
    public void deleteContainers(Long user_id) {
        List<Container> li = em.createQuery("select c from Container c where c.user_id =: user_id")
                .setParameter("user_id", user_id)
                .getResultList();
        if(li.size() != 0){
            for(int i=0; i<li.size(); i++) {
                containerRepository.delete(li.get(i));
            }
        }
    }
}
