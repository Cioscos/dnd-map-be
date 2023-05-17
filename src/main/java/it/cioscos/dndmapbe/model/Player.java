package it.cioscos.dndmapbe.model;

import lombok.Data;

import java.util.List;

@Data
public class Player {
    private String name;
    private String color;
    private Position position;
    private String sessionToken;

    private boolean movementCompleted;

    private List<CastedAttack> castedAttacks;
}
