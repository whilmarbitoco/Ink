package com.whilmarbitoco.inkspace.model;


import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

@Table(name = "Edition")
public class Edition {

    @Primary
    @Column(name = "EditionID")
    private int editionID;

    @Column(name = "Edition")
    private String edition;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "BookID")
    private int BookID;

    public Edition() {}

    public Edition(String edition, String isbn, int bookID) {
        this.edition = edition;
        this.isbn = isbn;
        BookID = bookID;
    }

    public int getEditionID() {
        return editionID;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    @Override
    public String toString() {
        return this.edition;
    }
}
