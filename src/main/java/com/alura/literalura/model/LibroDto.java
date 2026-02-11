package com.alura.literalura.model;

public record LibroDto(
        Long id,
        String titulo,
        Autor autor,
        String idioma,
        Double numeroDeDescarga) {
    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idioma='" + idioma + '\'' +
                ", numeroDeDescarga=" + numeroDeDescarga ;
    }
}
