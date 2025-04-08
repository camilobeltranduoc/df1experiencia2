package com.duoc.peliculas.service;

import com.duoc.peliculas.exception.PeliculaNotFoundException;
import com.duoc.peliculas.model.Pelicula;
import com.duoc.peliculas.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository repo;

    public List<Pelicula> obtenerTodas() {
        return repo.findAll();
    }

    public Pelicula obtenerPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }
}