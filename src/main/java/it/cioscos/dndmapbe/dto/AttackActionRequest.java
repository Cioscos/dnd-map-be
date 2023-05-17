package it.cioscos.dndmapbe.dto;

import it.cioscos.dndmapbe.model.CastedAttack;
import it.cioscos.dndmapbe.model.Player;
import lombok.Data;

@Data
public class AttackActionRequest {
    private String sessionName;
    private Player player;
    private CastedAttack castedAttack;
}
