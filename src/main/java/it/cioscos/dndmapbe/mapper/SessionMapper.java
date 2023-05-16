package it.cioscos.dndmapbe.mapper;

import it.cioscos.dndmapbe.document.Session;
import it.cioscos.dndmapbe.dto.SessionDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

    private final ModelMapper modelMapper;

    public SessionMapper() {
        this.modelMapper = new ModelMapper();
    }

    public SessionDto toDto(Session session) {
        return modelMapper.map(session, SessionDto.class);
    }

    public Session toDocument(SessionDto sessionDto) {
        return modelMapper.map(sessionDto, Session.class);
    }
}
