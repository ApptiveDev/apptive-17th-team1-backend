package com.example.wineapi.domain.wine.dto;

import com.example.wineapi.domain.wine.entity.Wine;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WineInfoDto {
    private Long id;
    private String name;    // 이름
    private Integer sugar;  // 당도
    private Integer tannin; // 타닌
    private Integer body;   // 바디감
    private Integer acidity;   // 탄산
    private String flavor;  // 향
    private String food;   // 어울리는 음식
    private String variety;    // 품종
    private String country;    // 생산국
    private String type;   // 종류
    private String alcohol; // 알콜 도수

    public WineInfoDto(Wine wine) {
        this.id = wine.getId();
        this.name = wine.getName();
        this.sugar = wine.getSugar();
        this.tannin = wine.getTannin();
        this.body = wine.getBody();
        this.acidity = wine.getAcidity();
        this.flavor = new String();
        this.food = new String();
        this.variety = new String();
        this.country = new String();
        this.type = new String();
        this.alcohol = new String();
    }

    public WineInfoDto(Long id, String name, Integer sugar, Integer tannin, Integer body, Integer acidity) {
        this.id = id;
        this.name = name;
        this.sugar = sugar;
        this.tannin = tannin;
        this.body = body;
        this.acidity = acidity;
        this.flavor = new String();
        this.food = new String();
        this.variety = new String();
        this.country = new String();
        this.type = new String();
        this.alcohol = new String();
    }
}
