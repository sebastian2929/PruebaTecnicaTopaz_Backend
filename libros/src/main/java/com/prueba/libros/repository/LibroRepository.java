package com.prueba.libros.repository;

import com.prueba.libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LibroRepository
        extends JpaRepository<Libro, Long>,
                JpaSpecificationExecutor<Libro> {
}
