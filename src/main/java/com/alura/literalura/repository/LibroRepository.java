package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.AutorDatos;
import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface LibroRepository extends JpaRepository<Libro, Long>{

    @Query("SELECT l FROM Libro l WHERE l.idioma=:idioma ")
    List<Libro> listarLibrosPorIdioma(String idioma);
}
