package com.example.wineapi.data.dto.question;

public class AnswerDto {
    private Long user_id;   // 사용자 index
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
    private Integer alcohol; // 알콜 도수

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public Integer getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Integer alcohol) {
        this.alcohol = alcohol;
    }
}
