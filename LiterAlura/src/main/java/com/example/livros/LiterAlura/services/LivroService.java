package com.example.livros.LiterAlura.services;


import com.example.livros.LiterAlura.DTOs.DadosAutor;
import com.example.livros.LiterAlura.DTOs.DadosLivro;
import com.example.livros.LiterAlura.DTOs.DadosResposta;
import com.example.livros.LiterAlura.models.Autor;
import com.example.livros.LiterAlura.models.Livro;
import com.example.livros.LiterAlura.repositories.AutorRepository;
import com.example.livros.LiterAlura.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private ConsumoApi consumoApi;
    
    private final String ENDERECO_BASE = "https://gutendex.com/books/";
    
    public void buscarLivrosPorTitulo(String titulo) {
        String endereco = ENDERECO_BASE + "?search=" + titulo.replace(" ", "%20");
        DadosResposta resposta = consumoApi.obterDados(endereco, DadosResposta.class);
        
        if (resposta.livros() != null && !resposta.livros().isEmpty()) {
            System.out.println("📚 Livros encontrados: " + resposta.livros().size());
            resposta.livros().forEach(this::salvarLivro);
        } else {
            System.out.println("❌ Nenhum livro encontrado com o título: " + titulo);
        }
    }
    
    public void buscarLivrosPorAutor(String nomeAutor) {
        String endereco = ENDERECO_BASE + "?search=" + nomeAutor.replace(" ", "%20");
        DadosResposta resposta = consumoApi.obterDados(endereco, DadosResposta.class);
        
        if (resposta.livros() != null && !resposta.livros().isEmpty()) {
            System.out.println("📚 Livros encontrados: " + resposta.livros().size());
            resposta.livros().forEach(this::salvarLivro);
        } else {
            System.out.println("❌ Nenhum livro encontrado para o autor: " + nomeAutor);
        }
    }
    
    public void listarLivrosCadastrados() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("📚 Nenhum livro cadastrado no banco de dados.");
        } else {
            System.out.println("📚 Livros cadastrados (" + livros.size() + "):");
            livros.forEach(System.out::println);
        }
    }
    
    public void listarAutoresCadastrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("👤 Nenhum autor cadastrado no banco de dados.");
        } else {
            System.out.println("👤 Autores cadastrados (" + autores.size() + "):");
            autores.forEach(System.out::println);
        }
    }
    
    public void listarAutoresVivosNoAno(Integer ano) {
        List<Autor> autores = autorRepository.findAutoresVivosNoAno(ano);
        if (autores.isEmpty()) {
            System.out.println("👤 Nenhum autor encontrado vivo no ano " + ano);
        } else {
            System.out.println("👤 Autores vivos no ano " + ano + ":");
            autores.forEach(System.out::println);
        }
    }
    
    public void listarLivrosPorIdioma(String idioma) {
        List<Livro> livros = livroRepository.findByIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("📚 Nenhum livro encontrado no idioma: " + idioma);
        } else {
            System.out.println("📚 Livros em " + idioma + ":");
            livros.forEach(System.out::println);
        }
    }
    
    public void top10LivrosMaisBaixados() {
        List<Livro> topLivros = livroRepository.findTop10ByOrderByNumeroDownloadsDesc();
        if (topLivros.isEmpty()) {
            System.out.println("📚 Nenhum livro encontrado.");
        } else {
            System.out.println("🏆 Top 10 livros mais baixados:");
            topLivros.forEach(System.out::println);
        }
    }
    
    private void salvarLivro(DadosLivro dadosLivro) {
        if (livroRepository.existsById(dadosLivro.id())) {
            System.out.println("⚠️ Livro já existe: " + dadosLivro.titulo());
            return;
        }
        
        Autor autor = null;
        if (dadosLivro.autores() != null && !dadosLivro.autores().isEmpty()) {
            DadosAutor dadosAutor = dadosLivro.autores().get(0);
            autor = buscarOuCriarAutor(dadosAutor);
        }
        
        Livro livro = new Livro(
            dadosLivro.id(),
            dadosLivro.titulo(),
            autor,
            dadosLivro.idiomas(),
            dadosLivro.numeroDownloads()
        );
        
        livroRepository.save(livro);
        System.out.println("✅ Livro salvo: " + dadosLivro.titulo());
    }
    
    private Autor buscarOuCriarAutor(DadosAutor dadosAutor) {
        Optional<Autor> autorExistente = autorRepository.findByNome(dadosAutor.nome());
        
        if (autorExistente.isPresent()) {
            return autorExistente.get();
        }
        
        Autor novoAutor = new Autor(
            dadosAutor.nome(),
            dadosAutor.anoNascimento(),
            dadosAutor.anoFalecimento()
        );
        
        return autorRepository.save(novoAutor);
    }
}
    
