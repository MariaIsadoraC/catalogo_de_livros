package com.catalogo.catalogo_livro;

import java.util.List;
import java.util.Map; 

public class Book {
    private String title;
    private List<Author> authors;
    private List<String> languages;
    private int download_count;
    private int birth_year;
    private int death_year;

    public int getDeath_year() {
        return death_year;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public int getDownloads() {
        return download_count;
    }

    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }
}



