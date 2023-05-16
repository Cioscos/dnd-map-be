package it.cioscos.dndmapbe.service;

import it.cioscos.dndmapbe.dto.SessionDto;
import it.cioscos.dndmapbe.model.Player;

public interface DndService {
    SessionDto createSession(String name);

    SessionDto getSessionById(String name);

    SessionDto addPlayerToSession(String sessionName, Player player);

    SessionDto removePlayerToSession(String sessionName, Player player);

    SessionDto movePlayer(String sessionName, Player player);
}
