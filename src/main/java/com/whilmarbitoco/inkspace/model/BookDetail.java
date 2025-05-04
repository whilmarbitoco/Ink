package com.whilmarbitoco.inkspace.model;

import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

import java.sql.Date;
import java.time.LocalDate;

@Table(name = "BookDetail")
public class BookDetail {

    @Primary
    @Column(name = "BookDetailID")
    private int bookDetailID;

    @Column(name = "Description")
    private String description;

    @Column(name = "PublishedDate")
    private Date publishedDate;

    @Column(name = "Publisher")
    private String publisher;

    @Column(name = "BookID")
    private int bookID;

    public BookDetail() {}

    public BookDetail(String description, Date publishedDate, String publisher, int bookID) {
        this.description = description;
        this.publishedDate = publishedDate;
        this.publisher = publisher;
        this.bookID = bookID;
    }

    public int getBookDetailID() {
        return bookDetailID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}
