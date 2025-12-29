package com.prueba.libros.service;

import com.prueba.libros.model.Libro;
import com.prueba.libros.repository.LibroRepository;
import com.prueba.libros.filter.LibroFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LibroService {

    private final LibroRepository repository;

    public LibroService(LibroRepository repository) {
        this.repository = repository;
    }

    public Page<Libro> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Libro> listar(
            Pageable pageable,
            String filterField,
            String filterOperator,
            String filterValue
    ) {

        if (filterField != null && !filterField.isBlank()
                && filterValue != null && !filterValue.isBlank()) {

            Specification<Libro> spec =
                    LibroFilter.apply(filterField, filterOperator, filterValue);

            return repository.findAll(spec, pageable);
        }

        return repository.findAll(pageable);
    }

    public Libro crear(Libro libro) {
        validarFechaPublicacion(libro.getFechaPublicacion());
        return repository.save(libro);
    }

    public Libro actualizar(Long id, Libro libro) {
        validarFechaPublicacion(libro.getFechaPublicacion());

        Libro existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        existente.setNombre(libro.getNombre());
        existente.setDescripcion(libro.getDescripcion());
        existente.setAutor(libro.getAutor());
        existente.setFechaPublicacion(libro.getFechaPublicacion());
        existente.setNumeroEjemplares(libro.getNumeroEjemplares());
        existente.setCosto(libro.getCosto());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private void validarFechaPublicacion(LocalDate fecha) {
        if (fecha.isBefore(LocalDate.now().minusYears(10))) {
            throw new RuntimeException(
                "No se permiten libros con más de 10 años de publicación"
            );
        }
    }
}
