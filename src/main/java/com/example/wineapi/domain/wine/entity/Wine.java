package com.example.wineapi.domain.wine.entity;

import javax.persistence.*;

@Entity
@Table(name = "Wine")
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer sugar;  // 당도
    private Integer tannin; // 타닌
    private Integer body;   // 바디감
    private Integer carbonicAcid;   // 탄산
    private Integer smell;  // 향
    private Integer mood;   // 분위기
    private Integer pairing;    // 페어링
    private Integer variety;    // 품종
    private Integer country;    // 생산국
    private Integer type;   // 종류
    private Double alcohol; // 알콜 도수

    public Wine() {
        this.id = Long.valueOf(0);
    }

    public Wine(Long id, String name, Integer sugar, Integer tannin, Integer body, Integer carbonicAcid, Integer smell, Integer mood, Integer pairing, Integer variety, Integer country, Integer type, Double alcohol) {
        this.id = id;
        this.name = name;
        this.sugar = sugar;
        this.tannin = tannin;
        this.body = body;
        this.carbonicAcid = carbonicAcid;
        this.smell = smell;
        this.mood = mood;
        this.pairing = pairing;
        this.variety = variety;
        this.country = country;
        this.type = type;
        this.alcohol = alcohol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSugar() {
        return sugar;
    }

    public void setSugar(Integer sugar) {
        this.sugar = sugar;
    }

    public Integer getTannin() {
        return tannin;
    }

    public void setTannin(Integer tannin) {
        this.tannin = tannin;
    }

    public Integer getBody() {
        return body;
    }

    public void setBody(Integer body) {
        this.body = body;
    }

    public Integer getCarbonicAcid() {
        return carbonicAcid;
    }

    public void setCarbonicAcid(Integer carbonicAcid) {
        this.carbonicAcid = carbonicAcid;
    }

    public Integer getSmell() {
        return smell;
    }

    public void setSmell(Integer smell) {
        this.smell = smell;
    }

    public Integer getMood() {
        return mood;
    }

    public void setMood(Integer mood) {
        this.mood = mood;
    }

    public Integer getPairing() {
        return pairing;
    }

    public void setPairing(Integer pairing) {
        this.pairing = pairing;
    }

    public Integer getVariety() {
        return variety;
    }

    public void setVariety(Integer variety) {
        this.variety = variety;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Double alcohol) {
        this.alcohol = alcohol;
    }
}
