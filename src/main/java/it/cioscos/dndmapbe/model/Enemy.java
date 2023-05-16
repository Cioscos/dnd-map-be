package it.cioscos.dndmapbe.model;

import lombok.Data;

@Data
public class Enemy {
    private String name;
    private String color;
    private Position position;
    private int size;
}
