package com.example.wineapi.data.dto.member;

public class ContainerDTO {
    private Long user_id;
    private Long wine_id;
    private boolean is_like;



    public ContainerDTO(Long user_id, Long wine_id, boolean is_like) {
        this.user_id = user_id;
        this.wine_id = wine_id;
        this.is_like = is_like;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    @Override
    public String toString() {
        return "ContainerDTO{" +
                "user_id=" + user_id +
                ", wine_id=" + wine_id +
                ", is_like=" + is_like +
                '}';
    }
}
