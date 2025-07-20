
// Principal.java
package com.example.livros.LiterAlura.principal;

import com.example.livros.LiterAlura.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.livros.LiterAlura.services.LivroService;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class Principal {
    
    @Autowired
    private LivroService livroService;
    
    private Scanner leitura = new Scanner(System.in);
    
    public void exibeMenu() {
        var opcao = -1;
        
        while (opcao != 0) {
            var menu = """
                    
                    ╔══════════════════════════════════════════════════════════════════════════════════════╗
                    ║                           📚 CATÁLOGO DE LIVROS 📚                                   ║
                    ╠══════════════════════════════════════════════════════════════════════════════════════╣
                    ║                                                                                      ║
                    ║  1️⃣  - Buscar livro pelo título                                                      ║
                    ║  2️⃣  - Buscar livros por autor                                                       ║
                    ║  3️⃣  - Listar livros cadastrados                                                     ║
                    ║  4️⃣  - Listar autores cadastrados                                                    ║
                    ║  5️⃣  - Listar autores vivos em determinado ano                                       ║
                    ║  6️⃣  - Listar livros em determinado idioma                                           ║
                    ║  7️⃣  - Top 10 livros mais baixados                                                   ║
                    ║  8️⃣  - Buscar autor por nome                                                         ║
                    ║  9️⃣  - Estatísticas do catálogo                                                      ║
                    ║                                                                                      ║
                    ║  0️⃣  - Sair                                                                          ║
                    ║                                                                                      ║
                    ╚══════════════════════════════════════════════════════════════════════════════════════╝
                    
                    🔍 Escolha uma opção:""";
            
            System.out.println(menu);
            
            try {
                opcao = leitura.nextInt();
                leitura.nextLine(); // Consome a quebra de linha
                
                switch (opcao) {
                    case 1:
                        buscarLivroPorTitulo();
                        break;
                    case 2:
                        buscarLivrosPorAutor();
                        break;
                    case 3:
                        livroService.listarLivrosCadastrados();
                        break;
                    case 4:
                        livroService.listarAutoresCadastrados();
                        break;
                    case 5:
                        listarAutoresVivosNoAno();
                        break;
                    case 6:
                        listarLivrosPorIdioma();
                        break;
                    case 7:
                        livroService.top10LivrosMaisBaixados();
                        break;
                    case 8:
                        buscarAutorPorNome();
                        break;
                    case 9:
                        exibirEstatisticas();
                        break;
                    case 0:
                        System.out.println("👋 Obrigado por usar o Catálogo de Livros! Até logo!");
                        break;
                    default:
                        System.out.println("❌ Opção inválida! Tente novamente.");
                }
                
                if (opcao != 0) {
                    System.out.println("\n⏸️ Pressione Enter para continuar...");
                    leitura.nextLine();
                }
                
            } catch (Exception e) {
                System.out.println("❌ Erro: Digite apenas números!");
                leitura.nextLine(); // Limpa o buffer
            }
        }
    }
    
    private void buscarLivroPorTitulo() {
        System.out.println("📖 Digite o título do livro que deseja buscar:");
        var titulo = leitura.nextLine();
        
        if (titulo.trim().isEmpty()) {
            System.out.println("❌ Título não pode estar vazio!");
            return;
        }
        
        System.out.println("🔍 Buscando livros com título: " + titulo);
        livroService.buscarLivrosPorTitulo(titulo);
    }
    
    private void buscarLivrosPorAutor() {
        System.out.println("👤 Digite o nome do autor:");
        var nomeAutor = leitura.nextLine();
        
        if (nomeAutor.trim().isEmpty()) {
            System.out.println("❌ Nome do autor não pode estar vazio!");
            return;
        }
        
        System.out.println("🔍 Buscando livros do autor: " + nomeAutor);
        livroService.buscarLivrosPorAutor(nomeAutor);
    }
    
    private void listarAutoresVivosNoAno() {
        System.out.println("📅 Digite o ano para buscar autores vivos:");
        try {
            var ano = leitura.nextInt();
            leitura.nextLine(); // Consome a quebra de linha
            
            if (ano < 1 || ano > 2024) {
                System.out.println("❌ Digite um ano válido (1-2024)!");
                return;
            }
            
            livroService.listarAutoresVivosNoAno(ano);
        } catch (Exception e) {
            System.out.println("❌ Digite um ano válido!");
            leitura.nextLine(); // Limpa o buffer
        }
    }
    
    private void listarLivrosPorIdioma() {
        System.out.println("""
                🌍 Escolha o idioma:
                
                es - Espanhol
                en - Inglês
                fr - Francês
                pt - Português
                de - Alemão
                it - Italiano
                
                💬 Digite o código do idioma:""");
        
        var idioma = leitura.nextLine().toLowerCase();
        
        if (idioma.trim().isEmpty()) {
            System.out.println("❌ Código do idioma não pode estar vazio!");
            return;
        }
         livroService.listarLivrosPorIdioma(idioma);
    }

      private void buscarAutorPorNome() {
        System.out.println("👤 Digite o nome do autor para buscar:");
        var nomeAutor = leitura.nextLine();
        
        if (nomeAutor.trim().isEmpty()) {
            System.out.println("❌ Nome do autor não pode estar vazio!");
            return;
        }
        
        // Implementar busca de autor específico
        System.out.println("🔍 Funcionalidade em desenvolvimento...");
    }

     private void exibirEstatisticas() {
        System.out.println("""
                📊 ESTATÍSTICAS DO CATÁLOGO
                ════════════════════════════════════════
                
                🔍 Funcionalidade em desenvolvimento...
                
                Em breve você poderá ver:
                • Total de livros cadastrados
                • Total de autores cadastrados  
                • Idioma mais comum
                • Autor com mais livros
                • Média de downloads
                ════════════════════════════════════════
                """);
    }
}

//
