package com.alura.literalura.model;

public record LibroDto(
        Long id,
        String titulo,
        String autor,
        String idioma,
        Double numeroDeDescarga) {
}
