package it.cioscos.dndmapbe.exception;

public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException(String name) {
        super("Session not found with name: " + name);
    }
}
