package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.AutorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {

    // el usuario da un año determinado
    // buscamos autores cuyo nacimiento sea menor o igual al año
    // y que no hayan muerto aún o murieron después de ese año

    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anioVivo AND " +
            "(a.fechaDeDefuncion  > :anioVivo OR a.fechaDeDefuncion IS NULL) ")
    List<Autor> autoresVivosEn(Integer anioVivo);
}
