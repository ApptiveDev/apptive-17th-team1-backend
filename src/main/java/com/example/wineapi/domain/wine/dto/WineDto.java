package com.example.wineapi.domain.wine.dto;

import com.example.wineapi.domain.wine.entity.Wine;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WineDto {
    private Long id;
    private String name;
    private Integer sugar;  // 당도
    private Integer tannin; // 타닌
    private Integer body;   // 바디감
    private Integer acidity;   // 산도
    private Integer flavor;  // 향
    private Integer food;   // 분위기
    private Integer variety;    // 품종
    private Integer country;    // 생산국
    private Integer type;   // 종류
    private Integer alcohol; // 알콜 도수

    public WineDto(Wine wine) {
        this.id = wine.getId();
        this.name = wine.getName();
        this.sugar = wine.getSugar();
        this.tannin = wine.getTannin();
        this.body = wine.getBody();
        this.acidity = wine.getAcidity();
        this.flavor = wine.getFlavor();
        this.food = wine.getFood();
        this.variety = wine.getVariety();
        this.country = wine.getCountry();
        this.type = wine.getType();
        this.alcohol = wine.getAlcohol();
    }
}
