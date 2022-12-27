package com.example.wineapi.domain.wine.controller;

import com.example.wineapi.domain.wine.dto.WineDto;
import com.example.wineapi.domain.wine.dto.WineInfoDto;
import com.example.wineapi.domain.wine.service.WineService;
import com.example.wineapi.global.error.exception.NotFoundImageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/wine")
public class WineController {
    private final WineService wineService;

    @Autowired
    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @RequestMapping(value = "/id/v1/{id}", method = RequestMethod.GET)
    public ResponseEntity<WineDto> wineById(@PathVariable Long id) {
        WineDto wineDto = wineService.wineDtoById(id);
        if (wineDto.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(wineDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/id/v2/{id}", method = RequestMethod.GET)
    public ResponseEntity<WineInfoDto> wineInfoById(@PathVariable Long id) {
        WineInfoDto wineInfoDto = wineService.wineInfoDtoById(id);
        if (wineInfoDto.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(wineInfoDto, HttpStatus.OK);
    }

    @GetMapping(value = "/image/{wineImageFileOriginName}")
    public ResponseEntity<Resource> getImageByName(@PathVariable("wineImageFileOriginName") String fileName) throws NotFoundImageException {
        try {
            String path = "resources/img";
            FileSystemResource resource = new FileSystemResource(path+fileName);
            if (!resource.exists()) {
                throw new NotFoundImageException();
            }
            HttpHeaders header = new HttpHeaders();
            Path filePath = null;
            filePath = Paths.get(path+fileName);
            header.add("Content-Type", Files.probeContentType(filePath));
            return new ResponseEntity<Resource>((Resource) resource, header, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundImageException();
        }
    }
}
