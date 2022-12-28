package com.example.wineapi.domain.container.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
@Table(name = "Container")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private Long wine_id;

    private LocalDate localDate;
    private LocalTime localTime;

    private boolean is_like;

    public Container() {
        this.user_id = null;
        this.wine_id = null;
        this.is_like = false;
    }

    public Container(Long user_id, Long wine_id, LocalDate localDate, LocalTime localTime, boolean is_like) {
        this.id = id;
        this.user_id = user_id;
        this.wine_id = wine_id;
        this.localDate = localDate;
        this.localTime = localTime;
        this.is_like = is_like;
    }

    public Container(Long user_id, Long wine_id, boolean is_like) {
        this.user_id = user_id;
        this.wine_id = wine_id;
        this.is_like = is_like;
    }

    public boolean getIs_like() {
        return this.is_like;
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
