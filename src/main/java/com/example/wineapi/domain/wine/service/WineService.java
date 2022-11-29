package com.example.wineapi.domain.wine.service;

import com.example.wineapi.domain.wine.dto.WineDto;
import com.example.wineapi.domain.wine.entity.Wine;
import com.example.wineapi.domain.wine.repository.WineRepository;
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
        WineDto wineDto = new WineDto(wineRepository.findWineById(id).orElse(new Wine()));
        return wineDto;
    }
}
