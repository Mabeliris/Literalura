package com.alura.literalura.service;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {
    @Autowired
    private LibroRepository repository;
    private AutorRepository autorRepository;


    public LibroService(LibroRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }

    public  List<LibroDto> convierteDatos(List<Libro> libros){
        return libros.stream()
                .map(l->new LibroDto(l.getId(),l.getTitulo(),l.getAutor(),l.getIdioma(),l.getNumeroDeDescarga()))
                .collect(Collectors.toList());
    }

    public  List<AutorDto> convierteDatosAutor(List<Autor> autores){
        return autores.stream()
                .map(a->new AutorDto(a.getNombre(), a.getFechaNacimiento(), a.getFechaDeDefuncion()))
                .collect(Collectors.toList());
    }
    public void guardarLibroConAutor(LibroDatos datos, Autor autor) {

        autorRepository.save(autor);

        Libro libro = new Libro(datos, autor);
        repository.save(libro);
    }

    public List<LibroDto> librosRegistrados() {
        return convierteDatos((repository.findAll())) ;
    }

    public List<AutorDto> autoresRegistrados() {

       return convierteDatosAutor(( autorRepository.findAll()));

    }

    public List<AutorDto> autoresVivosEn(int anioVivo){

        return convierteDatosAutor((autorRepository.autoresVivosEn(anioVivo)));
    }


    public List<LibroDto> listarLibrosPorIdioma(String idioma) {
        return convierteDatos((repository.listarLibrosPorIdioma(idioma))) ;
    }
}
