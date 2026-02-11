package com.alura.literalura.model;

public record AutorDto(
        String nombre,
        Integer fechaNacimiento,
        Integer fechaDeDefuncion
) {
    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaDeDefuncion=" + fechaDeDefuncion;
    }
}
