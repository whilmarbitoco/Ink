package com.whilmarbitoco.inkspace.model;


import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

@Table(name = "BookCover")
public class BookCover {

    @Primary
    private int skip;

    @Column(name = "BookID")
    private int bookID;

    @Column(name = "CoverTypeID")
    private int coverID;

    public int getBookID() {
        return bookID;
    }

    public int getCoverID() {
        return coverID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setCoverID(int coverID) {
        this.coverID = coverID;
    }
}
