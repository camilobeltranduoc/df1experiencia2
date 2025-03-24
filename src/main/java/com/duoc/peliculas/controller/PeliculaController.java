package com.duoc.peliculas.controller;

import org.springframework.web.bind.annotation.*;

import com.duoc.peliculas.model.Pelicula;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController() {
        peliculas.add(new Pelicula(1, "Mi Película Favorita", 2020, "Director Ejemplo", "Drama", "Una breve descripción de la película."));
        peliculas.add(new Pelicula(2, "La Aventura", 2021, "Ana Pérez", "Aventura", "Un viaje inesperado."));
        peliculas.add(new Pelicula(3, "Terror Nocturno", 2022, "Carlos Gómez", "Terror", "Pesadillas que se vuelven realidad."));
        peliculas.add(new Pelicula(4, "Amor en el Aire", 2019, "Laura Díaz", "Romance", "Una historia de amor inolvidable."));
        peliculas.add(new Pelicula(5, "Futuro Incierto", 2023, "Miguel Torres", "Ciencia Ficción", "Un futuro donde todo cambia."));
    }

    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        return peliculas;
    }

    @GetMapping("/{id}")
    public Pelicula getPeliculaById(@PathVariable int id) {
        return peliculas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
