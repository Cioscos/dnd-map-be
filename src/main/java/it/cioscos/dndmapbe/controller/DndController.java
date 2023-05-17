package it.cioscos.dndmapbe.controller;

import it.cioscos.dndmapbe.dto.PlayerMovementRequest;
import it.cioscos.dndmapbe.dto.SessionDto;
import it.cioscos.dndmapbe.model.Player;
import it.cioscos.dndmapbe.service.DndService;
import it.cioscos.dndmapbe.util.Common;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("dnd-map/api")
@Slf4j
public class DndController {

    private final DndService service;

    private final SimpMessagingTemplate template;

    @GetMapping("/session/new/{name}")
    SessionDto createSession(@PathVariable String name, @RequestParam String size) {
        log.info(Common.createLog("Name: " + name));
        return service.createSession(name, size);
    }

    @GetMapping("/session/{name}")
    SessionDto getSessionByName(@PathVariable String name) {
        log.info(Common.createLog("Name: " + name));
        return service.getSessionById(name);
    }

    @PostMapping("/session/{sessionName}/players")
    SessionDto addPlayerToSession(@PathVariable String sessionName, @RequestBody Player player) {
        log.info(Common.createLog("sessionName: " + sessionName + " player: " + player));
        return service.addPlayerToSession(sessionName, player);
    }

    @DeleteMapping("/session/{sessionName}/players")
    SessionDto removePlayerToSession(@PathVariable String sessionName, @RequestBody Player player) {
        log.info(Common.createLog("sessionName: " + sessionName + " player: " + player));
        return service.removePlayerToSession(sessionName, player);
    }

    @MessageMapping("/session/player/move")
    void movePlayer(@RequestBody PlayerMovementRequest player) {
        log.info(Common.createLog("Player: " + player));
        var session = service.movePlayer(player);

        // Send the updated session to all subscribers
        template.convertAndSend("/topic/sessionUpdate", session);
    }
}
