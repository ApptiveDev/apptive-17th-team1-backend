package com.example.wineapi.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    TOKEN_MISS(BAD_REQUEST, "토큰이 없습니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "계정 정보가 존재하지 않습니다"),
    WRONG_PASSWORD(UNAUTHORIZED, "비밀번호가 틀렸습니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    QUESTION_NOT_FOUND(NOT_FOUND, "존재하지 않는 카테고리 입니다."),
    WINE_NOT_FOUND(NOT_FOUND, "해당 와인을 찾을 수 없습니다."),
    IMAGE_NOT_FOUND(NOT_FOUND, "해당 이미지를 찾을 수 없습니다"),
    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "로그아웃 된 사용자입니다"),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_MEMBER_NAME(CONFLICT, "사용자 계정이 이미 존재합니다"),
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
