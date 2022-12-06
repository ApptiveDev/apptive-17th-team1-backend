package com.example.wineapi.domain.wine.controller;

import com.example.wineapi.domain.wine.dto.WineDto;
import com.example.wineapi.domain.wine.service.WineService;
import com.example.wineapi.global.error.ErrorCode;
import com.example.wineapi.global.error.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
            throw new CustomException(ErrorCode.WINE_NOT_FOUND);
        }

        return new ResponseEntity<>(wineDto, HttpStatus.OK);
    }

    // TODO 와인 이미지 API 작성
//    @GetMapping(value = "/image/{wineImageFileOriginName}")
//    public ResponseEntity<Resource> getImageByName(@PathVariable("wineImageFileOriginName") String fileName) throws ImageNotFoundException {
//        try {
//            String path = "resources/img";
//            FileSystemResource resource = new FileSystemResource(path+fileName);
//            if (!resource.exists()) {
//                throw new ImageNotFoundException();
//            }
//            HttpHeaders header = new HttpHeaders();
//            Path filePath = null;
//            filePath = Paths.get(path+fileName);
//            header.add("Content-Type", Files.probeContentType(filePath));
//            return new ResponseEntity<Resource>((Resource) resource, header, HttpStatus.OK);
//        } catch (Exception e) {
//            throw new ImageNotFoundException();
//        }
//    }
}
