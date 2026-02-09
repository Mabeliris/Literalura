package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    private String idioma;
    private Double numeroDeDescarga;

    public Libro(){};

    public Libro(LibroDatos datos) {

        this.titulo = datos.titulo();
        if (!datos.idioma().isEmpty()) {
            this.idioma = datos.idioma().get(0);
        } else {
            this.idioma = "";
        }
        this.numeroDeDescarga = datos.numeroDeDescarga();
    }

    public Libro(ResultApi datos) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescarga() {
        return numeroDeDescarga;
    }

    public void setNumeroDeDescarga(Double numeroDeDescarga) {
        this.numeroDeDescarga = numeroDeDescarga;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idioma='" + idioma + '\'' +
                ", numeroDeDescarga=" + numeroDeDescarga +
                '}';
    }
}
