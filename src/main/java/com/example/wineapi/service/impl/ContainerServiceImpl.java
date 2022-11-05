package com.example.wineapi.service.impl;

import com.example.wineapi.data.dao.ContainerDAO;
import com.example.wineapi.data.dto.ContainerDTO;
import com.example.wineapi.data.entity.member.Container;
import com.example.wineapi.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerServiceImpl implements ContainerService {
    private final ContainerDAO containerDAO;

    @Autowired
    public ContainerServiceImpl(ContainerDAO containerDAO) {
        this.containerDAO = containerDAO;
    }

    @Override
    public ContainerDTO saveContainer(ContainerDTO containerDTO) {
        Container container = new Container();
        container.setUser_id(containerDTO.getUser_id());
        container.setWine_id(containerDTO.getWine_id());
        container.setIs_like(containerDTO.getIs_like());

        Container savedContainer = containerDAO.insertContainer(container);

        ContainerDTO containerResponseDTO = new ContainerDTO(savedContainer.getUser_id(),
                                                             savedContainer.getWine_id(),
                                                             savedContainer.getIs_like());
        return containerResponseDTO;
    }

    @Override
    public ContainerDTO getContainer(Long id) {
        Container container = containerDAO.selectContainer(id);

        ContainerDTO containerResponseDTO = new ContainerDTO(container.getUser_id(),
                                                             container.getWine_id(),
                                                             container.getIs_like());

        return containerResponseDTO;
    }

    public List<Long> getMyContainers(Long user_id) {
        List<Long> li = containerDAO.selectMyContainers(user_id);
        if(li==null)
            return null;

        return li;
    }

    @Override
    public void deleteContainer(Long user_id, Long wine_id){
        containerDAO.deleteContainer(user_id, wine_id);
    }
}
