package com.prueba.libros.service;

import com.prueba.libros.model.Libro;
import com.prueba.libros.repository.LibroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibroServiceTest {

    @Mock
    private LibroRepository repository;

    @InjectMocks
    private LibroService service;

    @Test
    void crearLibro_valido_debeGuardar() {
        Libro libro = libroValido();

        when(repository.save(any(Libro.class))).thenReturn(libro);

        Libro resultado = service.crear(libro);

        assertNotNull(resultado);
        verify(repository, times(1)).save(libro);
    }

    @Test
    void crearLibro_conMasDe10Anios_debeLanzarError() {
        Libro libro = libroValido();
        libro.setFechaPublicacion(LocalDate.now().minusYears(15));

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> service.crear(libro)
        );

        assertEquals(
                "No se permiten libros con más de 10 años de publicación",
                ex.getMessage()
        );

        verify(repository, never()).save(any());
    }

    @Test
    void actualizarLibro_existente_debeActualizar() {
        Libro existente = libroValido();
        existente.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any(Libro.class))).thenReturn(existente);

        Libro actualizado = service.actualizar(1L, existente);

        assertNotNull(actualizado);
        verify(repository).findById(1L);
        verify(repository).save(existente);
    }

    private Libro libroValido() {
        Libro libro = new Libro();
        libro.setNombre("Clean Code");
        libro.setDescripcion("Buenas prácticas");
        libro.setAutor("Robert C. Martin");
        libro.setFechaPublicacion(LocalDate.now().minusYears(2));
        libro.setNumeroEjemplares(10);
        libro.setCosto(new BigDecimal("99.9900"));
        return libro;
    }
}
