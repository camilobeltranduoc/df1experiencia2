package com.duoc.peliculas.service;

import com.duoc.peliculas.exception.PeliculaNotFoundException;
import com.duoc.peliculas.model.Pelicula;
import com.duoc.peliculas.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public Pelicula guardar(Pelicula pelicula) {
        // Si se envía un ID y ya existe en la BD, lanzamos conflicto
        if (pelicula.getId() != null && repo.existsById(pelicula.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La película con id " + pelicula.getId() + " ya existe");
        }
        return repo.save(pelicula);
    }

    // Método para actualizar una película existente
    public Pelicula actualizar(Long id, Pelicula peliculaActualizada) {
        // Verificamos si la película existe
        Optional<Pelicula> peliculaOpt = repo.findById(id);
        if (!peliculaOpt.isPresent()) {
            throw new PeliculaNotFoundException(id);
        }
        
        Pelicula peliculaExistente = peliculaOpt.get();
        // Actualizamos cada atributo según corresponda
        peliculaExistente.setTitulo(peliculaActualizada.getTitulo());
        peliculaExistente.setAnio(peliculaActualizada.getAnio());
        peliculaExistente.setDirector(peliculaActualizada.getDirector());
        peliculaExistente.setGenero(peliculaActualizada.getGenero());
        peliculaExistente.setSinopsis(peliculaActualizada.getSinopsis());
        
        return repo.save(peliculaExistente);
    }
    
    // Método para eliminar una película por su id
    public void eliminar(Long id) {
        // Verificamos si la película existe; si no, lanzamos excepción
        if (!repo.existsById(id)) {
            throw new PeliculaNotFoundException(id);
        }
        repo.deleteById(id);
    }
}