package com.example.wineapi.data.dao.Impl;

import com.example.wineapi.data.dao.ContainerDAO;
import com.example.wineapi.data.entity.Container;
import com.example.wineapi.data.repository.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ContainerDAOImpl implements ContainerDAO {
    private final ContainerRepository containerRepository;

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
    public void deleteContainer(Long id) throws Exception {
        Optional<Container> selectedContainer = containerRepository.findById(id);

        if(selectedContainer.isPresent()) {
            Container container = selectedContainer.get();

            containerRepository.delete(container);
        }
        else {
            throw new Exception();
        }
    }
}
