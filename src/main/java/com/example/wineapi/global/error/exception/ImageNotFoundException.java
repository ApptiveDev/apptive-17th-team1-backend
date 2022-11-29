package com.example.wineapi.global.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Image Not Found")
public class ImageNotFoundException extends Exception{

    private final int ERROR_CODE;

    public ImageNotFoundException(int ERROR_CODE) {
        super("이미지를 찾을 수 없습니다");
        this.ERROR_CODE = ERROR_CODE;
    }

    public ImageNotFoundException() {
        this(404);
    }

    public int getERROR_CODE() {
        return ERROR_CODE;
    }
}
