package com.alura.literalura.model;

import java.util.Optional;

public class Autor {
    private Long id;
    private String nombre;
    private Integer fechaNacimiento;
    private Optional<Integer> fechaDeDefuncion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Optional<Integer> getFechaDeDefuncion() {
        return fechaDeDefuncion;
    }

    public void setFechaDeDefuncion(Optional<Integer> fechaDeDefuncion) {
        this.fechaDeDefuncion = fechaDeDefuncion;
    }
}
