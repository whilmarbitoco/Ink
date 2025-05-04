package com.whilmarbitoco.inkspace.model;


import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

@Table(name = "Genre")
public class Genre {

    @Primary
    @Column(name = "GenreID")
    private int genreID;

    @Column(name = "GenreName")
    private String genre;

    @Column(name = "Description")
    private String description;

    public Genre() {}

    public Genre(String genre, String description) {
        this.genre = genre;
        this.description = description;
    }

    public int getGenreID() {
        return genreID;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
