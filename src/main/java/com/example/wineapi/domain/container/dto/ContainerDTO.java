package com.example.wineapi.domain.container.dto;

import com.example.wineapi.domain.container.entity.Container;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class ContainerDTO {
    private Long wine_id;
    private boolean is_like;
    private LocalDate localDate;
    private LocalTime localTime;

    public ContainerDTO(Long user_id, Long wine_id, boolean is_like) {
        this.wine_id = wine_id;
        this.is_like = is_like;
        localDate = LocalDate.now();
        localTime = LocalTime.now();
    }

    public ContainerDTO(Container container) {
        this.wine_id = container.getWine_id();
        this.is_like = container.getIs_like();
        localDate = container.getLocalDate();
        localTime = container.getLocalTime();
    }

    public boolean getIs_like() {
        return this.is_like;
    }
}
