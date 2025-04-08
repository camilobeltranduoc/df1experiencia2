package com.duoc.peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.duoc.peliculas.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    // Por ahora no necesitamos agregar métodos adicionales.
}
