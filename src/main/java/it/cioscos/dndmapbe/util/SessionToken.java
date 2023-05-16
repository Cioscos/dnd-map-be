package it.cioscos.dndmapbe.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class SessionToken {
    private static final String DIVIDER = "#";

    public String generateToken(String userName) {
        return userName + DIVIDER + LocalDateTime.now() + DIVIDER + UUID.randomUUID();
    }
}
