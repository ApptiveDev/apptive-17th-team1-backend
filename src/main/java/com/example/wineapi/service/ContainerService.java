package com.example.wineapi.service;

import com.example.wineapi.data.dto.ContainerDTO;

public interface ContainerService {
    ContainerDTO saveContainer(ContainerDTO containerDTO);
    ContainerDTO getContainer(Long id);
    void deleteContainer(Long id) throws Exception;
}
