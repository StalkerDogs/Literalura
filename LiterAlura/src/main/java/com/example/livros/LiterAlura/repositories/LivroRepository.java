package com.example.livros.LiterAlura.repositories;

import com.example.livros.LiterAlura.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    @Query("SELECT l FROM Livro l WHERE l.autor.nome ILIKE %:nomeAutor%")
    List<Livro> findByAutorNomeContainingIgnoreCase(@Param("nomeAutor") String nomeAutor);
    
    @Query("SELECT l FROM Livro l WHERE l.titulo ILIKE %:titulo%")
    List<Livro> findByTituloContainingIgnoreCase(@Param("titulo") String titulo);
    
    @Query("SELECT l FROM Livro l JOIN l.idiomas i WHERE i = :idioma")
    List<Livro> findByIdioma(@Param("idioma") String idioma);
    
    @Query("SELECT l FROM Livro l ORDER BY l.numeroDownloads DESC")
    List<Livro> findTop10ByOrderByNumeroDownloadsDesc();
}
