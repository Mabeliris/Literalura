package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.model.LibroDatos;
import com.alura.literalura.model.ResultApi;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConvierteDatos;
import com.alura.literalura.service.LibroService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {

    private final Scanner teclado = new Scanner(System.in);
    private final LibroService service;
    private final LibroRepository repository;
    private static final String URL_BASE = "https://gutendex.com/books/" ;
    private final ConsumoApi consumoApi= new ConsumoApi();
    private final ConvierteDatos convierteDatos= new ConvierteDatos();
    private String json;
    private List<Libro> libro;
    private final List <LibroDatos> datosLibros = new ArrayList<>();



    public Principal(LibroService service, LibroRepository repository) {
        this.service = service;
        this.repository=repository;
    }

    public void menu() {
        var json= consumoApi.obtenerDatos(URL_BASE);
        System.out.println(json);

        var datos= convierteDatos.obtenerDatos(json, ResultApi.class);
        System.out.println(datos);
        var opcion= -1;
        while (opcion!=0){
            System.out.println("***********************************************");
            System.out.println("Bienvenido a Literalura, elige una opción a través de su número: ");
            System.out.println("***********************************************");
            System.out.println("""
                1. Buscar Libro por titulo
                2. Listar libros registrados
                3. Listar autores registrados
                4. Listar autores vivos en un determinado año
                5. Listar libros por idioma
                0. salir
                """);

            opcion = teclado.nextInt();
            teclado.nextLine();
            
            switch (opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    librosRegistrados();
                    break;
                case 3:
                    autoresRegistrados();
                case 4:
                    autoresVivos();
                case 5:
                    listarLibrosPorIdioma();

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");

            }

        }

    }

    private LibroDatos buscarLibroEnApi() {

        System.out.println("Ingrese el titulo del libro que desea buscar");
        var tituloIngresado= teclado.nextLine();
        json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloIngresado.replace(" ", "+"));
        var datosBusqueda=convierteDatos.obtenerDatos(json, ResultApi.class);

        Optional<LibroDatos> libroBuscado= datosBusqueda.libros().stream()
                .filter(l->l.titulo().toUpperCase().contains(tituloIngresado.toUpperCase()))
                .findFirst();

        if(libroBuscado.isPresent()){
            LibroDatos datos = libroBuscado.get();
            System.out.println("Libro encontrado");
            System.out.println(datos);
            return datos;
        }else {

            return null;

        }
    }

    private Autor convertirAutor(LibroDatos datos) {

        var autorDto = datos.autor().get(0);

        return new Autor(
                autorDto.nombre(),
                autorDto.fechaNacimiento(),
                autorDto.fechaDeDefuncion()
        );
    }


    private void buscarLibroPorTitulo() {

        LibroDatos datos = buscarLibroEnApi();

        if (datos != null) {

            Autor autor = convertirAutor(datos);
            service.guardarLibroConAutor(datos, autor);

            System.out.println("Libro guardado correctamente");

        } else {
            System.out.println("No se encontró ningún libro con ese título.");
        }
    }


    private void librosRegistrados() {


           List<Libro> librosRegistrados= service.librosRegistrados();
           System.out.println(librosRegistrados);
    }

    private void autoresRegistrados() {

        List<Autor> autoresRegistrados = service.autoresRegistrados();
        System.out.println(autoresRegistrados);

    }

    private void autoresVivos() {
        System.out.println("ingresa el año que desea revisar: ");
        var anioVivo= teclado.nextInt();
        teclado.nextLine();
        List<Autor> autoresvivos=service.autoresVivosEn(anioVivo);

        if (autoresvivos.isEmpty()){
            System.out.println("No se han encontrado autores vivos para este año");
        }else{
            System.out.println(autoresvivos);
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("¿En qué idioma necesita revisar libros? ");
        var idiomas= teclado.nextLine().substring(0,2);
        List<Libro> listarLibrosPorIdioma= service.listarLibrosPorIdioma(idiomas);
        System.out.println("Estos son los libros en " + idiomas + ":" + listarLibrosPorIdioma);


    }

}
