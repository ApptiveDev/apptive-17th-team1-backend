package com.example.wineapi.domain.question.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AnswerDto {
    private Long user_id;   // 사용자 index
    private Integer sugar;  // 당도
    private Integer tannin; // 타닌
    private Integer body;   // 바디감
    private Integer acidity;   // 탄산
    private Integer smell;  // 향
    private Integer food;   // 분위기
    private Integer variety;    // 품종
    private Integer country;    // 생산국
    private Integer type;   // 종류
    private Integer alcohol; // 알콜 도수
}
