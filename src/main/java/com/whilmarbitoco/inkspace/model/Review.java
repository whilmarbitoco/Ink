package com.whilmarbitoco.inkspace.model;


import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

import java.sql.Date;
import java.time.LocalDateTime;

@Table(name = "BookRating")
public class Review {

    @Primary
    @Column(name = "RatingID")
    private int ratingID;

    @Column(name = "UserID")
    private int userID;

    @Column(name = "BookID")
    private int bookID;

    @Column(name = "Rating")
    private int rating;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "CreatedAt")
    private LocalDateTime created;

    public Review() {}

    public Review(int userID, int bookID, int rating, String comment) {
        this.userID = userID;
        this.bookID = bookID;
        this.rating = rating;
        this.comment = comment;
        this.created = LocalDateTime.now();
    }

    public int getRatingID() {
        return ratingID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
