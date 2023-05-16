package it.cioscos.dndmapbe.service;

import it.cioscos.dndmapbe.dto.SessionDto;

public interface DndService {
    SessionDto createSession(String name);

    SessionDto getSessionById(String name);
}
