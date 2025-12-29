package com.prueba.libros.controller;

import com.prueba.libros.model.Libro;
import com.prueba.libros.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {

    private final LibroService service;

    public LibroController(LibroService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Libro> listar(
            Pageable pageable,
            @RequestParam(required = false) String filterField,
            @RequestParam(required = false) String filterOperator,
            @RequestParam(required = false) String filterValue
    ) {
        return service.listar(pageable, filterField, filterOperator, filterValue);
    }

    @PostMapping
    public Libro crear(@Valid @RequestBody Libro libro) {
        return service.crear(libro);
    }

    @PutMapping("/{id}")
    public Libro actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Libro libro
    ) {
        return service.actualizar(id, libro);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
