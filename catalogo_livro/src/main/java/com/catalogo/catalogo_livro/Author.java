package com.catalogo.catalogo_livro;

public class Author {
    private String name;
    private int birth_year;
    private int death_year;

    public Author(String name, int birth_year, int death_year) {
        this.name = name;
        this.birth_year = birth_year;
        this.death_year = death_year;
    }

    public void setDeath_year(int death_year) {
        this.death_year = death_year;
    }


    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }
    

    public String getName() {
        return name;
    }

    public int getBirth_year() {
        return birth_year;
    }
    public int getDeath_year() {
        return death_year;
    }
}
