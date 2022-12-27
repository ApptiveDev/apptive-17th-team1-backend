package com.example.wineapi.domain.wine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "Wine")
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Wine() {
        this.id = Long.valueOf(0);
    }

    public Wine(Long id, String name, Integer sugar, Integer tannin, Integer body, Integer carbonicAcid, Integer smell, Integer mood, Integer pairing, Integer variety, Integer country, Integer type, Integer alcohol) {
        this.id = id;
        this.name = name;
        this.sugar = sugar;
        this.tannin = tannin;
        this.body = body;
        this.acidity = carbonicAcid;
        this.flavor = smell;
        this.food = mood;
        this.variety = variety;
        this.country = country;
        this.type = type;
        this.alcohol = alcohol;
    }
}
