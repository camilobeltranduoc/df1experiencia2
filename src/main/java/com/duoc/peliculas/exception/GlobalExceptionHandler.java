package com.duoc.peliculas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PeliculaNotFoundException.class)
    public ResponseEntity<Object> handlePeliculaNotFound(PeliculaNotFoundException ex) {

        // Creamos un mapa para construir el cuerpo de la respuesta
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());          // Fecha y hora del error
        body.put("status", HttpStatus.NOT_FOUND.value());      // Código 404
        body.put("error", "Not Found");                        // Mensaje estándar
        body.put("message", ex.getMessage());                  // Mensaje específico de la excepción

        // Retornamos la respuesta con código 404
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
