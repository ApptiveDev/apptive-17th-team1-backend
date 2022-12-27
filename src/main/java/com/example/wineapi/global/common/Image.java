package com.example.wineapi.global.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String originFileName;
    private String storeFileName;

    @Builder
    public Image(Long id, String originFileName, String storeFileName) {
        this.id = id;
        this.originFileName = originFileName;
        this.storeFileName = storeFileName;
    }
}
