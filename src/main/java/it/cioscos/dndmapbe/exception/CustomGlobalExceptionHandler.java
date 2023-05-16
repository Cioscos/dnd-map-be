package it.cioscos.dndmapbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<Object> illegalArgumentExceptionHandler(IllegalArgumentException ex, WebRequest request) {
        var errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        errors.setPath(request.getDescription(false));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SessionNotFoundException.class)
    protected ResponseEntity<Object> sessionNotFoundExceptionHandler(SessionNotFoundException ex, WebRequest request) {
        var errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        errors.setPath(request.getDescription(false));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
