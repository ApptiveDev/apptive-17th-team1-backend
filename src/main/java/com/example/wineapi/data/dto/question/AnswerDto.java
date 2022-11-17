package com.example.wineapi.data.dto.question;

public class AnswerDto {
    private Integer sugar;  // 당도
    private Integer tannin; // 타닌
    private Integer body;   // 바디감
    private Integer carbonicAcid;   // 탄산
    private Integer smellId;  // 향
    private Integer moodId;   // 분위기
    private Integer pairingId;    // 페어링
    private Integer varietyId;    // 품종
    private Integer countryId;    // 생산국
    private Integer typeId;   // 종류
    private Double alcoholId; // 알콜 도수

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

    public Integer getSmellId() {
        return smellId;
    }

    public void setSmellId(Integer smellId) {
        this.smellId = smellId;
    }

    public Integer getMoodId() {
        return moodId;
    }

    public void setMoodId(Integer moodId) {
        this.moodId = moodId;
    }

    public Integer getPairingId() {
        return pairingId;
    }

    public void setPairingId(Integer pairingId) {
        this.pairingId = pairingId;
    }

    public Integer getVarietyId() {
        return varietyId;
    }

    public void setVarietyId(Integer varietyId) {
        this.varietyId = varietyId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Double getAlcoholId() {
        return alcoholId;
    }

    public void setAlcoholId(Double alcoholId) {
        this.alcoholId = alcoholId;
    }
}
