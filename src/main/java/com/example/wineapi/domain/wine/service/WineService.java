package com.example.wineapi.domain.wine.service;

import com.example.wineapi.domain.wine.dto.WineDto;
import com.example.wineapi.domain.wine.dto.WineInfoDto;
import com.example.wineapi.domain.wine.entity.Wine;
import com.example.wineapi.domain.wine.entity.wineProperty.*;
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

    public WineInfoDto wineInfoDtoById(Long id) {
        Wine wine = wineRepository.findWineById(id).orElse(new Wine());
        WineInfoDto wineInfoDto = new WineInfoDto(wine);
        wineInfoDto.setFlavor(wineRepository.selectFlavor(wine.getFlavor()).orElse(new Flavor()).getContent());
        wineInfoDto.setFood(wineRepository.selectFood(wine.getFood()).orElse(new Food()).getContent());
        wineInfoDto.setVariety(wineRepository.selectVariety(wine.getVariety()).orElse(new Variety()).getContent());
        wineInfoDto.setCountry(wineRepository.selectCountry(wine.getCountry()).orElse(new Country()).getContent());
        wineInfoDto.setType(wineRepository.selectType(wine.getType()).orElse(new Type()).getContent());
        wineInfoDto.setAlcohol(wineRepository.selectAlcohol(wine.getAlcohol()).orElse(new Alcohol()).getContent());

        return wineInfoDto;
    }
}
