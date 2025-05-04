package com.whilmarbitoco.inkspace.model;


import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

@Table(name = "BookGenre")
public class BookGenre {

    @Primary
    private int skip;

    @Column(name = "BookID")
    private int bookID;

    @Column(name = "GenreID")
    private int genreID;

    public int getBookID() {
        return bookID;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }
}
