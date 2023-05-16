package it.cioscos.dndmapbe.service.impl;

import it.cioscos.dndmapbe.document.Session;
import it.cioscos.dndmapbe.dto.SessionDto;
import it.cioscos.dndmapbe.exception.SessionNotFoundException;
import it.cioscos.dndmapbe.mapper.SessionMapper;
import it.cioscos.dndmapbe.model.MapSize;
import it.cioscos.dndmapbe.model.Player;
import it.cioscos.dndmapbe.repository.SessionRepository;
import it.cioscos.dndmapbe.service.DndService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DndServiceImpl implements DndService {

    private final SessionRepository repository;
    private final SessionMapper sessionMapper;

    @Override
    public SessionDto createSession(String name, String size) {
        var session = new Session();
        session.setName(name);
        session.setTurnFinished(false);

        // calculate map size
        String[] sizeParts = size.split("x");

        if (sizeParts.length == 2) {
            int width = Integer.parseInt(sizeParts[0]);
            int height = Integer.parseInt(sizeParts[1]);

            var mapSize = new MapSize();
            mapSize.setWidth(width);
            mapSize.setHeight(height);
            session.setMapSize(mapSize);
        } else {
            throw new IllegalArgumentException("Wrong size format");
        }

        // insert the session and retrieve the saved session
        return sessionMapper.toDto(repository.insert(session));
    }

    @Override
    public SessionDto getSessionById(String name) {
        var session = repository.findSessionByName(name).orElseThrow(() ->
                new SessionNotFoundException(name));

        return sessionMapper.toDto(session);
    }

    @Override
    public SessionDto addPlayerToSession(String sessionName, Player player) {
        var session = repository.findSessionByName(sessionName).orElseThrow(() ->
                new SessionNotFoundException(sessionName));

        session.addPlayer(player);

        return sessionMapper.toDto(repository.save(session));
    }

    @Override
    public SessionDto removePlayerToSession(String sessionName, Player player) {
        var session = repository.findSessionByName(sessionName).orElseThrow(() ->
                new SessionNotFoundException(sessionName));

        session.removePlayer(player);

        return sessionMapper.toDto(repository.save(session));
    }

    @Override
    public SessionDto movePlayer(String sessionName, Player player) {
        var session = repository.findSessionByName(sessionName).orElseThrow(() ->
                new SessionNotFoundException(sessionName));

        session.movePlayer(player);

        return sessionMapper.toDto(repository.save(session));
    }
}
