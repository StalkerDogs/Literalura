package com.example.livros.LiterAlura.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "livro_idiomas", joinColumns = @JoinColumn(name = "livro_id"))
    private List<String> idiomas;

    private Integer numeroDownloads;

    public Livro() {}

    public Livro(Long id, String titulo, Autor autor, List<String> idiomas, Integer numeroDownloads) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;
        this.numeroDownloads = numeroDownloads;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    public List<String> getIdiomas() { return idiomas; }
    public void setIdiomas(List<String> idiomas) { this.idiomas = idiomas; }

    public Integer getNumeroDownloads() { return numeroDownloads; }
    public void setNumeroDownloads(Integer numeroDownloads) { this.numeroDownloads = numeroDownloads; }

    @Override
    public String toString() {
        return "📚 " + titulo + 
               "\n   Autor: " + (autor != null ? autor.getNome() : "Desconhecido") +
               "\n   Idiomas: " + idiomas +
               "\n   Downloads: " + numeroDownloads;
    }
}
