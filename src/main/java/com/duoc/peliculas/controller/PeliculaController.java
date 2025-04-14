package com.duoc.peliculas.controller;

import com.duoc.peliculas.model.Pelicula;
import com.duoc.peliculas.model.ResponseWrapper; 
import com.duoc.peliculas.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        List<Pelicula> peliculas = peliculaService.obtenerTodas();

        if (peliculas.isEmpty()) {
            // Se devuelve un wrapper con estado "ERROR", count 0 y el mensaje de error como dato o mensaje personalizado.
            ResponseWrapper<List<Pelicula>> response = new ResponseWrapper<>("ERROR", 0, peliculas);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Si hay datos, se devuelven envueltos en ResponseWrapper.
        ResponseWrapper<List<Pelicula>> response = new ResponseWrapper<>("OK", peliculas.size(), peliculas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        // Llama al servicio, que lanza la excepción si no se encuentra,
        // de lo contrario se envuelve el objeto en ResponseWrapper.
        Pelicula pelicula = peliculaService.obtenerPorId(id);
        ResponseWrapper<Pelicula> response = new ResponseWrapper<>("OK", 1, pelicula);
        return ResponseEntity.ok(response);
    }

    // Endpoint para crear una nueva película
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Pelicula pelicula) {
        // Aquí se guarda la película en la base de datos
        Pelicula peliculaGuardada = peliculaService.guardar(pelicula);
        ResponseWrapper<Pelicula> response = new ResponseWrapper<>("OK", 1, peliculaGuardada);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody @Valid Pelicula pelicula) {
        Pelicula peliculaActualizada = peliculaService.actualizar(id, pelicula);
        ResponseWrapper<Pelicula> response = new ResponseWrapper<>("OK", 1, peliculaActualizada);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        peliculaService.eliminar(id);
        ResponseWrapper<String> response = new ResponseWrapper<>("OK", 0, "La película ha sido eliminada correctamente");
        return ResponseEntity.ok(response);
    }
}
