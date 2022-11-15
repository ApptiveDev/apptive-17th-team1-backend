package com.example.wineapi.controller;

import com.example.wineapi.data.dto.wine.WineDto;
import com.example.wineapi.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WineController {
    private final WineService wineService;

    @Autowired
    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @RequestMapping(value = "/wine/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<WineDto> wineById(@PathVariable Long id) {
        WineDto wineDto = wineService.wineDtoById(id);
        if (wineDto.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(wineDto, HttpStatus.OK);
    }
}
