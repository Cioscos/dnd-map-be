package it.cioscos.dndmapbe.dto;

import it.cioscos.dndmapbe.model.Player;
import lombok.Data;

@Data
public class PlayerActionRequest {
    private String sessionName;
    private Player player;
}
