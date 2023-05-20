package it.cioscos.dndmapbe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.cioscos.dndmapbe.dto.AttackActionRequest;
import it.cioscos.dndmapbe.dto.PlayerActionRequest;
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
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("dnd-map/api/session")
@Slf4j
public class DndController {

    private final DndService service;

    private final SimpMessagingTemplate template;

    @Operation(summary = "Create session passing a name as path variable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The session has been created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionDto.class))}),
            @ApiResponse(responseCode = "400", description = "The session doesn't exist",
                    content = @Content(mediaType = "application/json"))})
    @GetMapping("/new/{name}")
    SessionDto createSession(
            @Parameter(description = "Name of the new session") @PathVariable String name,
            @Parameter(description = "Size of the map. Example: 10x20") @RequestParam String size) {
        log.info(Common.createLog("Name: " + name));
        return service.createSession(name, size);
    }

    @Operation(summary = "Get the session by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the session",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionDto.class))}),
            @ApiResponse(responseCode = "400", description = "The session doesn't exist",
                    content = @Content(mediaType = "application/json"))})
    @GetMapping("/{name}")
    SessionDto getSessionByName(@Parameter(description = "Name of the new session") @PathVariable String name) {
        log.info(Common.createLog("Name: " + name));
        return service.getSessionById(name);
    }

    @Operation(summary = "Add the player to the session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the session",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionDto.class))}),
            @ApiResponse(responseCode = "400", description = "The session doesn't exist",
                    content = @Content(mediaType = "application/json"))})
    @PostMapping("/{sessionName}/players")
    SessionDto addPlayerToSession(
            @Parameter(description = "Name of the new session") @PathVariable String sessionName,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The player to add to the session") @RequestBody Player player) {
        log.info(Common.createLog("sessionName: " + sessionName + " player: " + player));
        return service.addPlayerToSession(sessionName, player);
    }

    @Operation(summary = "Remove the player to the session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the session",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionDto.class))}),
            @ApiResponse(responseCode = "400", description = "The session doesn't exist",
                    content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/{sessionName}/players")
    SessionDto removePlayerToSession(
            @Parameter(description = "Name of the new session") @PathVariable String sessionName,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The player to add to the session") @RequestBody Player player) {
        log.info(Common.createLog("sessionName: " + sessionName + " player: " + player));
        return service.removePlayerToSession(sessionName, player);
    }

    @MessageMapping("/player/attack")
    void playerAttack(@RequestBody AttackActionRequest action) {
        log.info(Common.createLog("Player: " + action));

        var session = service.playerAttack(action);

        // Send the updated session to all subscribers
        template.convertAndSend("/topic/sessionUpdate", session);
    }

    @MessageMapping("/player/move")
    void movePlayer(@RequestBody PlayerActionRequest player) {
        log.info(Common.createLog("Player: " + player));
        var session = service.movePlayer(player);

        // Send the updated session to all subscribers
        template.convertAndSend("/topic/sessionUpdate", session);
    }


}
