package it.cioscos.dndmapbe.service;

import it.cioscos.dndmapbe.dto.AttackActionRequest;
import it.cioscos.dndmapbe.dto.PlayerActionRequest;
import it.cioscos.dndmapbe.dto.SessionDto;
import it.cioscos.dndmapbe.model.Player;

public interface DndService {
    SessionDto createSession(String name, String size);

    SessionDto getSessionById(String name);

    SessionDto addPlayerToSession(String sessionName, Player player);

    SessionDto removePlayerToSession(String sessionName, Player player);

    SessionDto playerAttack(AttackActionRequest player);

    SessionDto movePlayer(PlayerActionRequest player);
}
