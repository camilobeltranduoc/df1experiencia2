package com.duoc.peliculas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PeliculaNotFoundException extends RuntimeException {

    public PeliculaNotFoundException(Long id) {
        // Generamos un mensaje personalizado que se mostrará en el error
        super("La película con id " + id + " no fue encontrada");
    }
}
