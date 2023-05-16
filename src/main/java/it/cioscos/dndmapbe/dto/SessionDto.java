package it.cioscos.dndmapbe.dto;

import it.cioscos.dndmapbe.model.Enemy;
import it.cioscos.dndmapbe.model.Entity;
import it.cioscos.dndmapbe.model.Player;
import lombok.Data;

import java.util.List;

@Data
public class SessionDto {
    private String name;

    private List<Player> players;

    private List<Enemy> enemies;

    private List<Entity> entities;
}
