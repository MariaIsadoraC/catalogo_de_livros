package com.catalogo.catalogo_livro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import com.google.gson.*;

import org.springframework.boot.SpringApplication;

public class CatalogoLivroApplication {
    private static listarLivrosRegistrados registroLivros = new listarLivrosRegistrados();
    


    public static void main(String[] args) {
        int opcao = -1;
        Scanner scanner = new Scanner(System.in);
        
        do{
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Buscar livro pelo título");
            System.out.println("2 - Listar livros resgistrados");
            System.out.println("3 - Listar autores resgistrados");
            System.out.println("4 - Listar autores vivos (registrados) em um determinado ano");
            System.out.println("5 - Listar livros de um determinado idioma (já registrados)");
            System.out.println("0 - Sair"); 

            try{
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e){
                System.out.println("Opção inválida");
                scanner.nextLine();
                continue;
            }
        ;

            switch(opcao){
                case 1:
                    System.out.println("Digite o título do livro:");
                    String titulo = scanner.nextLine().trim();
                    buscarLivro(titulo);
                    break;
                case 2:
                    registroLivros.listarLivros();
                    break;
                case 3:
                    registroLivros.listarAutores();
                    break;
                case 4:
                    System.out.println("Digite o ano:");
                    int ano = scanner.nextInt();
                    registroLivros.listarAutoresPorAno(ano);
                    break;
                case 5:
                    System.out.println("Insira o idioma para realizar a busca:");
                    System.out.println("pt- portugues\nen- ingles\nes- espanhol\nfr- frances");
                    String idioma = scanner.nextLine();
                    registroLivros.listarLivrosPorIdioma(idioma);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;

            }
        } while (opcao != 0);

        scanner.close();
}


private static void buscarLivro(String titulo) {
    String url = "https://gutendex.com/books/?search=" + titulo;
    
    CompletableFuture<String> resposta = httpRequestAsync(url);

    String jsonResponse = resposta.join();  

    processResponse(jsonResponse);
}


       private static CompletableFuture<String> httpRequestAsync(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                     .thenApply(HttpResponse::body);
    }

    private static void processResponse(String jsonResponse) {
        Gson gson = new GsonBuilder().setLenient().disableHtmlEscaping().create();
        BookResponse bookResponse = gson.fromJson(jsonResponse, BookResponse.class);
    
        if (bookResponse == null || bookResponse.getResults() == null || bookResponse.getResults().isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }
    
        
        Book book = bookResponse.getResults().get(0);
    
        System.out.println("Livro");
        System.out.println("---------------------");
        System.out.println("Título: " + book.getTitle());
    
        if (book.getAuthors() != null) {
            System.out.println("Autor(es): ");
            for (Author author : book.getAuthors()) {
                System.out.println("- " + author.getName());

                boolean autorExiste = registroLivros.autoresRegistrados.stream()
                .anyMatch(a -> a.getName().equals(author.getName()) &&
                               a.getBirth_year() == author.getBirth_year() &&
                               a.getDeath_year() == author.getDeath_year());

            if (!autorExiste) {
                registroLivros.autoresRegistrados.add(author);
            }
            }
        } else {
            System.out.println("Autor não encontrado.");
        }
    
        System.out.println("Idiomas: " + book.getLanguages());
        System.out.println("Downloads: " + book.getDownloads());
        System.out.println("---------------------");
        registroLivros.livrosRegistrados.add(book);
       
        
    }
}
    