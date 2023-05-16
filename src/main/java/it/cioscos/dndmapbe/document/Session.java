package it.cioscos.dndmapbe.document;

import it.cioscos.dndmapbe.model.Enemy;
import it.cioscos.dndmapbe.model.Entity;
import it.cioscos.dndmapbe.model.Player;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("session")
@Data
public class Session {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private List<Player> players;

    private List<Enemy> enemies;

    private List<Entity> entities;
}
