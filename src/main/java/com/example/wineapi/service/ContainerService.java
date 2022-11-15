package com.example.wineapi.service;

import com.example.wineapi.data.dto.member.ContainerDTO;

import java.util.List;

public interface ContainerService {
    ContainerDTO saveContainer(ContainerDTO containerDTO);
    ContainerDTO getContainer(Long id);
    void deleteContainer(Long user_id, Long wine_id);

    List<Long> getMyContainers(Long user_id);
}
