package it.cioscos.dndmapbe.model;

import lombok.Data;

@Data
public class Entity {
    private String color;
    private Position position;
    private int size;
}
