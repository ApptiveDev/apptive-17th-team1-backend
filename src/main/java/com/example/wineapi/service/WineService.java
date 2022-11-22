package com.example.wineapi.service;

import com.example.wineapi.data.dto.wine.WineDto;
import com.example.wineapi.data.entity.wine.Wine;
import com.example.wineapi.data.repository.wine.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WineService {


    private final WineRepository wineRepository;

    @Autowired
    public WineService(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    public WineDto wineDtoById(Long id) {
        WineDto wineDto = new WineDto(wineRepository.findById(id).orElse(new Wine()));
        return wineDto;
    }
}
