package it.cioscos.dndmapbe.repository;

import it.cioscos.dndmapbe.document.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends MongoRepository<Session, String> {

    Optional<Session> findSessionByName(String name);
}
