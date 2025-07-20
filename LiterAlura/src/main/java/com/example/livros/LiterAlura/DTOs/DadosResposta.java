package com.example.livros.LiterAlura.DTOs;


// DadosResposta.java

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResposta(
    @JsonAlias("count") Integer total,
    @JsonAlias("results") List<DadosLivro> livros
) {}