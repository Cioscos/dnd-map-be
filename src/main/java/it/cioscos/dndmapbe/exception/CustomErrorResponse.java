package it.cioscos.dndmapbe.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
}
