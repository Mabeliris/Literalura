package com.alura.literalura.service;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.model.LibroDatos;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private LibroRepository repository;
    private AutorRepository autorRepository;


    public LibroService(LibroRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }
    public void guardarLibroConAutor(LibroDatos datos, Autor autor) {

        autorRepository.save(autor);

        Libro libro = new Libro(datos, autor);
        repository.save(libro);
    }

    public List<Libro> librosRegistrados() {
       return repository.findAll();
    }

    public List<Autor> autoresRegistrados() {

       return autorRepository.findAll();

    }

    public List<Autor> autoresVivosEn(int anioVivo){

        return autorRepository.autoresVivosEn(anioVivo);
    }


    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return repository.listarLibrosPorIdioma(idioma);
    }
}
