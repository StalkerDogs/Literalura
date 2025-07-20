-- 01_create_schema.sql
-- Script para criar schema em vez de database separado

-- Criar o schema
CREATE SCHEMA IF NOT EXISTS catalogo_livros;

-- Definir o search_path para usar o schema
SET search_path TO catalogo_livros, public;

-- Tabela de autores
CREATE TABLE catalogo_livros.autores (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(500) UNIQUE NOT NULL,
    ano_nascimento INTEGER,
    ano_falecimento INTEGER
);

-- Tabela de livros
CREATE TABLE catalogo_livros.livros (
    id BIGINT PRIMARY KEY,
    titulo TEXT NOT NULL,
    autor_id BIGINT,
    numero_downloads INTEGER,
    FOREIGN KEY (autor_id) REFERENCES catalogo_livros.autores(id)
);

-- Tabela de idiomas dos livros
CREATE TABLE catalogo_livros.livro_idiomas (
    livro_id BIGINT,
    idiomas VARCHAR(10),
    PRIMARY KEY (livro_id, idiomas),
    FOREIGN KEY (livro_id) REFERENCES catalogo_livros.livros(id) ON DELETE CASCADE
);

-- Índices para melhorar performance
CREATE INDEX idx_autor_nome ON catalogo_livros.autores(nome);
CREATE INDEX idx_livro_titulo ON catalogo_livros.livros(titulo);
CREATE INDEX idx_livro_downloads ON catalogo_livros.livros(numero_downloads DESC);
CREATE INDEX idx_livro_autor ON catalogo_livros.livros(autor_id);
CREATE INDEX idx_livro_idiomas ON catalogo_livros.livro_idiomas(idiomas);

-- Dados de exemplo para teste
INSERT INTO catalogo_livros.autores (nome, ano_nascimento, ano_falecimento) VALUES
    ('Jane Austen', 1775, 1817),
    ('Charles Dickens', 1812, 1870),
    ('William Shakespeare', 1564, 1616),
    ('George Orwell', 1903, 1950),
    ('J.R.R. Tolkien', 1892, 1973),
    ('Agatha Christie', 1890, 1976),
    ('Mark Twain', 1835, 1910),
    ('Virginia Woolf', 1882, 1941),
    ('Oscar Wilde', 1854, 1900),
    ('F. Scott Fitzgerald', 1896, 1940);

INSERT INTO catalogo_livros.livros (id, titulo, autor_id, numero_downloads) VALUES
    (1, 'Pride and Prejudice', 1, 50000),
    (2, 'Emma', 1, 30000),
    (3, 'Great Expectations', 2, 45000),
    (4, 'A Tale of Two Cities', 2, 60000),
    (5, 'Romeo and Juliet', 3, 80000),
    (6, 'Hamlet', 3, 75000),
    (7, '1984', 4, 100000),
    (8, 'Animal Farm', 4, 85000),
    (9, 'The Hobbit', 5, 120000),
    (10, 'The Lord of the Rings', 5, 150000);

INSERT INTO catalogo_livros.livro_idiomas (livro_id, idiomas) VALUES
    (1, 'en'), (2, 'en'), (3, 'en'), (4, 'en'), (5, 'en'),
    (6, 'en'), (7, 'en'), (8, 'en'), (9, 'en'), (10, 'en');