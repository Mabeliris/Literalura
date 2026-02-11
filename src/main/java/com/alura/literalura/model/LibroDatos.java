package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDatos(@JsonAlias("title") String titulo,
                         @JsonAlias("languages") List<String> idioma,
                         @JsonAlias("authors") List<AutorDatos> autor,
                         @JsonAlias("download_count") Double numeroDeDescarga)
{


}
