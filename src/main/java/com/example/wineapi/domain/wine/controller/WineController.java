package com.example.wineapi.domain.wine.controller;

import com.example.wineapi.domain.wine.dto.WineDto;
import com.example.wineapi.domain.wine.dto.WineInfoDto;
import com.example.wineapi.domain.wine.service.WineService;
import com.example.wineapi.global.error.ErrorCode;
import com.example.wineapi.global.error.exception.CustomException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/wine")
public class WineController {
    private final WineService wineService;

    @Autowired
    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    /** 와인 id 기반 와인 조회 */
    @RequestMapping(value = "/id/v1/{id}", method = RequestMethod.GET)
    public ResponseEntity<WineDto> wineById(@PathVariable Long id) {
        WineDto wineDto = wineService.wineDtoById(id);
        if (wineDto.getId() == 0) {
            throw new CustomException(ErrorCode.WINE_NOT_FOUND);
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
    
    /** 와인 id 기반 와인이미지 조회 */
    @RequestMapping(value = "/image/id/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageByWineId(@PathVariable Long id) throws IOException {
        InputStream in = getClass().getResourceAsStream("/img/wine/" + Long.valueOf(id) + ".jpg");  // 경로 찾기
        if (in == null) throw new CustomException(ErrorCode.IMAGE_NOT_FOUND);
        return new ResponseEntity<>(IOUtils.toByteArray(in), HttpStatus.OK);
    }

    @RequestMapping(value = "/image/name/{wineName}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageByWineName(@PathVariable String wineName) throws IOException {
        InputStream in = getClass().getResourceAsStream("/img/" + wineName + ".jpg");  // 경로 찾기
        if (in == null) throw new CustomException(ErrorCode.IMAGE_NOT_FOUND);
        return new ResponseEntity<>(IOUtils.toByteArray(in), HttpStatus.OK);
    }
}
