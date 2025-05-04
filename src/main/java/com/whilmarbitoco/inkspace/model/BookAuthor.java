package com.whilmarbitoco.inkspace.model;


import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

@Table(name = "BookAuthor")
public class BookAuthor {

    @Primary
    private int skip;

    @Column(name = "BookID")
    private int bookID;

    @Column(name = "AuthorID")
    private int authorID;

    public int getBookID() {
        return bookID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }
}
