package com.prueba.libros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
    name = "libro",
    uniqueConstraints = @UniqueConstraint(columnNames = "nombre")
)
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150, unique = true)
    private String nombre;

    @NotBlank
    @Size(max = 300)
    @Column(nullable = false, length = 300)
    private String descripcion;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String autor;

    @NotNull
    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion;

    @NotNull
    @Min(0)
    @Column(name = "numero_ejemplares", nullable = false)
    private Integer numeroEjemplares;

    @NotNull
    @Digits(integer = 10, fraction = 4)
    @Column(nullable = false, precision = 14, scale = 4)
    private BigDecimal costo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public LocalDate getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(LocalDate fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public Integer getNumeroEjemplares() { return numeroEjemplares; }
    public void setNumeroEjemplares(Integer numeroEjemplares) { this.numeroEjemplares = numeroEjemplares; }

    public BigDecimal getCosto() { return costo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }
}
