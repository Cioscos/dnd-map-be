package it.cioscos.dndmapbe.document;

import it.cioscos.dndmapbe.model.Enemy;
import it.cioscos.dndmapbe.model.Entity;
import it.cioscos.dndmapbe.model.MapSize;
import it.cioscos.dndmapbe.model.Player;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Document("session")
@Data
public class Session {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private MapSize mapSize;

    private boolean turnFinished;

    private List<Player> players;

    private List<Enemy> enemies;

    private List<Entity> entities;

    public void addPlayer(Player player) {
        if (players == null) {
            players = new ArrayList<>();
        }

        // Check if a player with the same name already exists
        boolean playerExists = players.stream()
                .anyMatch(
                        existingPlayer -> existingPlayer
                                .getName()
                                .equals(player.getName())
                );
        if (playerExists) {
            // Check if a player with the same SessionToken already exists
            boolean sessionTokenEqual = players.stream()
                    .anyMatch(
                            existingPlayer -> existingPlayer
                                    .getSessionToken()
                                    .equals(player.getSessionToken())
                    );
            if (!sessionTokenEqual) {
                throw new IllegalArgumentException("Player with the same name already exists");
            }

        } else {
            // Check if a player with the same color already exists
            boolean playerColorExists = players.stream()
                    .anyMatch(
                            existingPlayer -> existingPlayer
                                    .getColor()
                                    .equals(player.getColor())
                    );
            if (playerColorExists) {
                throw new IllegalArgumentException("Player with the same color already exists.");
            }

            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        players = players.stream()
                .filter(p -> !p.getName().equals(player.getName()) || !p.getSessionToken().equals(player.getSessionToken()))
                .collect(Collectors.toList());
    }

    public void movePlayer(Player player) {
        for (Player p : players) {
            if (player.getName().equals(player.getName()) &&
                    player.getSessionToken().equals(player.getSessionToken())) {
                p.setPosition(player.getPosition());
            }
        }
    }

}
