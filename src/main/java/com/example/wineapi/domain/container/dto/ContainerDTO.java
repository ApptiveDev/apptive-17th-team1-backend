package com.example.wineapi.domain.container.dto;

import com.example.wineapi.domain.container.entity.Container;

public class ContainerDTO {
    private Long wine_id;
    private boolean is_like;



    public ContainerDTO(Long user_id, Long wine_id, boolean is_like) {
        this.wine_id = wine_id;
        this.is_like = is_like;
    }

    public ContainerDTO(Container container) {
        this.wine_id = container.getWine_id();
        this.is_like = container.getIs_like();
    }

    public Long getWine_id() {
        return wine_id;
    }

    public void setWine_id(Long wine_id) {
        this.wine_id = wine_id;
    }

    public boolean getIs_like() {
        return is_like;
    }

    public void setIs_like(boolean is_like) {
        this.is_like = is_like;
    }
}
