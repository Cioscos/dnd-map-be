package it.cioscos.dndmapbe.model;

import lombok.Data;

@Data
public class Player {
    private String name;
    private String color;
    private Position position;
    private String sessionToken;
}
