package com.catalogo.catalogo_livro;

import java.util.ArrayList;
import java.util.List;

public class listarLivrosRegistrados{
    public List<Book> livrosRegistrados = new ArrayList<>();
    public List<Author> autoresRegistrados = new ArrayList<>();

    public void setLivrosRegistrados(List<Book> livrosRegistrados) {
        this.livrosRegistrados = livrosRegistrados;
    }

    public void listarLivros() {
        if (livrosRegistrados.isEmpty()) {
            System.out.println("Nenhum livro registrado até o momento.");
        } else {
            System.out.println("Livros registrados:");
            System.out.println("---------------------");
            for (Book book : livrosRegistrados) {
                System.out.println("Título: " + book.getTitle());
                System.out.println("Autor(es): "+ book.getAuthors());
                System.out.println("Idiomas: " + book.getLanguages());
                System.out.println("Downloads: " + book.getDownloads());
                System.out.println("---------------------");
            }
        }
    }
    public void listarAutores() {
        if (autoresRegistrados.isEmpty()) {
            System.out.println("Nenhum autor registrado até o momento.");
        } else {
            System.out.println("Autores registrados:");
            for (Author author : autoresRegistrados) {
                System.out.println("Nome: " + author.getName());
                System.out.println("Ano de nascimento: " + (author.getBirth_year() != 0 ? author.getBirth_year() : "Desconhecido"));
                System.out.println("Ano de falecimento: " + (author.getDeath_year() != 0 ? author.getDeath_year() : "Desconhecido"));
                System.out.println("---------------------");
            }
        }
    }
    public void listarAutoresPorAno(int ano) {
        if (autoresRegistrados.isEmpty()) {
            System.out.println("Nenhum autor registrado até o momento.");
        } else {
        
            for (Author author : autoresRegistrados) {
                if(ano>=author.getBirth_year() && ano<=author.getDeath_year()){
                    System.out.println("Nome: " + author.getName());
                    System.out.println("Ano de nascimento: " + (author.getBirth_year() != 0 ? author.getBirth_year() : "Desconhecido"));
                    System.out.println("Ano de falecimento: " + (author.getDeath_year() != 0 ? author.getDeath_year() : "Desconhecido"));
                    System.out.println("---------------------");
                }
            }
        }
    }
    public void listarLivrosPorIdioma(String idioma) {
        boolean encontrou = false;

        for (Book book : livrosRegistrados) {
            if (book.getLanguages().contains(idioma)) {
                System.out.println("Título: " + book.getTitle());
                System.out.println("Autor(es): "+ book.getAuthors());
                System.out.println("Idiomas: " + book.getLanguages());
                System.out.println("Downloads: " + book.getDownloads());
                System.out.println("---------------------");
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum livro encontrado para o idioma: " + idioma);
        }
    }
}