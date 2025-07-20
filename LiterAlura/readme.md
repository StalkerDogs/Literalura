# 📚 Catálogo de Livros

## Descrição
Sistema de catálogo de livros desenvolvido em **Java 17** com **Spring Boot 3.2.3** que consome a API [Gutendex](https://gutendex.com) para buscar informações sobre livros e autores, armazenando os dados em um banco PostgreSQL.

## 🚀 Funcionalidades

### Menu Principal
O sistema oferece 9 opções de interação via console:

1. **Buscar livro pelo título** - Busca livros na API Gutendex por título
2. **Buscar livros por autor** - Busca livros de um autor específico
3. **Listar livros cadastrados** - Exibe todos os livros salvos no banco
4. **Listar autores cadastrados** - Exibe todos os autores salvos no banco
5. **Listar autores vivos em determinado ano** - Filtra autores por período
6. **Listar livros em determinado idioma** - Filtra livros por idioma
7. **Top 10 livros mais baixados** - Ranking por número de downloads
8. **Buscar autor por nome** - Busca específica de autores
9. **Estatísticas do catálogo** - Relatórios e métricas

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.3**
- **Spring Data JPA**
- **PostgreSQL**
- **Jackson 2.16** (para JSON)
- **Maven 4**
- **Docker & Docker Compose**

## 📋 Pré-requisitos

- JDK 17 ou superior
- Maven 4+
- PostgreSQL 12+ (ou Docker)
- Git

## 🔧 Configuração e Instalação

### 1. Clone o repositório
```bash
git clone [url-do-repositorio]
cd catalogo-livros
```

### 2. Configuração do Banco de Dados

#### Opção A: PostgreSQL Local
```sql
CREATE DATABASE catalogo_livros;
```

#### Opção B: Docker
```bash
docker run --name postgres-catalogo \
  -e POSTGRES_DB=catalogo_livros \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=senha123 \
  -p 5432:5432 \
  -d postgres:15-alpine
```

### 3. Configuração da Aplicação

Edite o arquivo `src/main/resources/application.properties`:

```properties
# Configuração do banco de dados
spring.datasource.url=jdbc:postgresql://localhost:5432/catalogo_livros
spring.datasource.username=postgres
spring.datasource.password=senha123

# Configuração do JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Executar a Aplicação

```bash
# Compilar e executar
mvn clean install
mvn spring-boot:run

# Ou executar o JAR
java -jar target/catalogo-livros-0.0.1-SNAPSHOT.jar
```

## 🐳 Execução com Docker

### Usando Docker Compose
```bash
# Subir toda a aplicação (banco + app)
docker-compose up --build

# Executar em background
docker-compose up -d --build

# Parar os serviços
docker-compose down
```

## 📊 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/catalogo/
│   │       ├── CatalogoLivrosApplication.java
│   │       ├── dto/
│   │       │   ├── DadosAutor.java
│   │       │   ├── DadosLivro.java
│   │       │   └── DadosResposta.java
│   │       ├── model/
│   │       │   ├── Autor.java
│   │       │   └── Livro.java
│   │       ├── repository/
│   │       │   ├── AutorRepository.java
│   │       │   └── LivroRepository.java
│   │       ├── service/
│   │       │   ├── ConsumoApi.java
│   │       │   ├── ConverteDados.java
│   │       │   └── LivroService.java
│   │       └── principal/
│   │           └── Principal.java
│   └── resources/
│       └── application.properties
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

## 🔍 Como Usar

### 1. Executar a Aplicação
```bash
mvn spring-boot:run
```

### 2. Navegar pelo Menu
- Use os números (1-9) para selecionar opções
- Digite 0 para sair
- Siga as instruções na tela para cada funcionalidade

### 3. Exemplos de Uso

#### Buscar Livro por Título
```
1️⃣ - Buscar livro pelo título
📖 Digite o título do livro que deseja buscar:
> Pride and Prejudice
```

#### Buscar por Autor
```
2️⃣ - Buscar livros por autor
👤 Digite o nome do autor:
> Jane Austen
```

#### Filtrar por Idioma
```
6️⃣ - Listar livros em determinado idioma
🌍 Escolha o idioma:
💬 Digite o código do idioma:
> en
```

## 🗄️ Banco de Dados

### Estrutura das Tabelas

**Tabela: autores**
- id (BIGINT, PK, AUTO_INCREMENT)
- nome (VARCHAR, UNIQUE)
- ano_nascimento (INTEGER)
- ano_falecimento (INTEGER)

**Tabela: livros**
- id (BIGINT, PK)
- titulo (TEXT)
- autor_id (BIGINT, FK)
- numero_downloads (INTEGER)

**Tabela: livro_idiomas**
- livro_id (BIGINT, FK)
- idiomas (VARCHAR)

## 🌐 API Gutendex

A aplicação consome a API do Project Gutenberg:
- **Base URL**: https://gutendex.com/books/
- **Busca por título**: `?search=titulo`
- **Busca por autor**: `?search=autor`
- **Documentação**: https://gutendex.com/

## 📈 Funcionalidades Implementadas

### ✅ Consumo de API
- HttpClient para requisições HTTP
- HttpRequest para configuração de requests
- HttpResponse para gerenciar respostas

### ✅ Processamento JSON
- Jackson ObjectMapper para conversão
- DTOs (Data Transfer Objects) para estruturação
- Mapeamento automático com @JsonAlias

### ✅ Persistência de Dados
- JPA/Hibernate para ORM
- PostgreSQL como banco de dados
- Relacionamentos entre entidades

### ✅ Interface de Usuário
- Menu interativo via console
- Tratamento de erros e validações
- Feedback visual com emojis

## 🚧 Melhorias Futuras

- [ ] Implementar busca avançada por autor
- [ ] Adicionar estatísticas detalhadas
- [ ] Cache para consultas frequentes
- [ ] Paginação para grandes resultados
- [ ] Testes unitários e de integração
- [ ] Interface web com Spring MVC
- [ ] API REST para consumo externo

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 📞 Contato

Para dúvidas ou sugestões:
- Email: [seu-email@example.com]
- GitHub: [seu-usuario]

---

**Desenvolvido com ❤️ usando Spring Boot e Java**