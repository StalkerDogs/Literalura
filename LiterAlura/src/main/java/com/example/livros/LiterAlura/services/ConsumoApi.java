package com.example.livros.LiterAlura.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ConsumoApi {
    
    private final HttpClient client;
    private final ObjectMapper mapper;
    
    public ConsumoApi() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }
    
    public String obterDados(String endereco) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao consumir API: " + e.getMessage(), e);
        }
    }
    
    public <T> T obterDados(String endereco, Class<T> classe) {
        String dados = obterDados(endereco);
        try {
            return mapper.readValue(dados, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter JSON: " + e.getMessage(), e);
        }
    }
}
    

