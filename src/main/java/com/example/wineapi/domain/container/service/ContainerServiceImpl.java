package com.example.wineapi.domain.container.service;

import com.example.wineapi.domain.container.dao.ContainerDAO;
import com.example.wineapi.domain.container.dto.ContainerDTO;
import com.example.wineapi.domain.container.dto.ContainerViewDto;
import com.example.wineapi.domain.container.entity.Container;
import com.example.wineapi.domain.wine.dto.WineDto;
import com.example.wineapi.domain.wine.service.WineService;
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
    public ContainerDTO getContainer(Long userId, Long wineId) {
        Container container = containerDAO.selectContainer(userId, wineId).orElse(new Container());
        ContainerDTO containerDTO = new ContainerDTO(container);

        return containerDTO;
    }

    @Override
    public List<ContainerViewDto> getMyContainers(Long user_id) {
        List<Container> li = containerDAO.selectMyContainers(user_id);
        List<ContainerViewDto> result = new ArrayList<>();
        for (int i = 0; i < li.size(); i++) {
            Container container = li.get(i);
            WineDto wineDto = wineService.wineDtoById(container.getWine_id());
            result.add(new ContainerViewDto(wineDto, container));
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
