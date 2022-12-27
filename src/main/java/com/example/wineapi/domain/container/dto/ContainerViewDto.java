package com.example.wineapi.domain.container.dto;

import com.example.wineapi.domain.container.entity.Container;
import com.example.wineapi.domain.wine.dto.WineDto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContainerViewDto {
    private Long id;
    private String name;
    private Integer sugar;  // 당도
    private Integer tannin; // 타닌
    private Integer body;   // 바디감
    private Integer acidity;   // 탄산
    private Integer flavor;  // 향
    private Integer food;   // 어울리는 음식
    private Integer variety;    // 품종
    private Integer country;    // 생산국
    private Integer type;   // 종류
    private Integer alcohol; // 알콜 도수
    private boolean is_like;    // 좋아요 여부

    public ContainerViewDto(WineDto wine, Container container) {
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
        this.is_like = container.getIs_like();
    }
}
