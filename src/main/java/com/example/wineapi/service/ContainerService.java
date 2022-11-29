package com.example.wineapi.service;

import com.example.wineapi.data.dto.container.ContainerViewDto;
import com.example.wineapi.data.dto.member.ContainerDTO;
import com.example.wineapi.data.entity.member.Container;

import java.util.List;

public interface ContainerService {
    ContainerDTO saveContainer(Long userId, ContainerDTO containerDTO);
    ContainerDTO getContainer(Long id);
    void deleteContainer(Long user_id, Long wine_id);

    void deleteContainers(Long user_id);

    List<ContainerViewDto> getMyContainers(Long user_id);
}
