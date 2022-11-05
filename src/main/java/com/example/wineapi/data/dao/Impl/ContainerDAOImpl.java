package com.example.wineapi.data.dao.Impl;

import com.example.wineapi.data.dao.ContainerDAO;
import com.example.wineapi.data.entity.member.Container;
import com.example.wineapi.data.repository.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public List<Long> selectMyContainers(Long user_id) {
        List<Long> li = em.createQuery("select c.wine_id from Container c where c.user_id =: user_id")
                .setParameter("user_id", user_id)
                .getResultList();
        if (li.size() == 0)
            return null;
        return li;
    }

    @Override
    public void deleteContainer(Long user_id, Long wine_id) {
        List<Container> li = em.createQuery("select c from Container c where c.user_id =: user_id and c.wine_id =: wine_id")
                .setParameter("user_id", user_id)
                .setParameter("wine_id", wine_id)
                .getResultList();
        if(li!=null) {
            containerRepository.delete(li.get(0));
        }
    }
}
