package com.example.wineapi.service.impl;

import com.example.wineapi.data.dao.ContainerDAO;
import com.example.wineapi.data.dto.container.ContainerViewDto;
import com.example.wineapi.data.dto.member.ContainerDTO;
import com.example.wineapi.data.entity.member.Container;
import com.example.wineapi.service.ContainerService;
import com.example.wineapi.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContainerServiceImpl implements ContainerService {
    private final ContainerDAO containerDAO;
    private final WineService wineService;

    @Autowired
    public ContainerServiceImpl(ContainerDAO containerDAO, WineService wineService) {
        this.containerDAO = containerDAO;
        this.wineService = wineService;
    }

    @Override
    public ContainerDTO saveContainer(Long userId, ContainerDTO containerDTO) {
        Container container = new Container(userId, containerDTO.getWine_id(), containerDTO.getIs_like());

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

    @Override
    public List<ContainerViewDto> getMyContainers(Long user_id) {
        List<Container> li = containerDAO.selectMyContainers(user_id);
        List<ContainerViewDto> result = new ArrayList<>();
        for (int i = 0; i < li.size(); i++) {
            Container container = li.get(i);
            result.add(new ContainerViewDto(wineService.wineDtoById(container.getWine_id()), container));
        }

        return result;
    }

    @Override
    public void deleteContainer(Long user_id, Long wine_id){
        containerDAO.deleteContainer(user_id, wine_id);
    }

    @Override
    public void deleteContainers(Long user_id) {
        containerDAO.deleteContainers(user_id);
    }
}
