package com.example.wineapi.data.entity.member;

import javax.persistence.*;

@Entity
@Table(name = "Container")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private Long wine_id;

    private boolean is_like;

    public Container() {
        this.user_id = null;
        this.wine_id = null;
        this.is_like = false;
    }

    public Container(Long user_id, Long wine_id, boolean is_like) {
        this.user_id = user_id;
        this.wine_id = wine_id;
        this.is_like = is_like;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "Container{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", wine_id=" + wine_id +
                ", is_like=" + is_like +
                '}';
    }
}
