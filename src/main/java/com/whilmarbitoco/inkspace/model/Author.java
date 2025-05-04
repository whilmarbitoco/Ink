package com.whilmarbitoco.inkspace.model;


import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

@Table(name = "Author")
public class Author {

    @Primary
    @Column(name = "AuthorID")
    private int authorID;

    @Column(name = "Name")
    private String name;

    @Column(name = "PenName")
    private String penName;

    @Column(name = "Biography")
    private String biography;

    @Column(name = "Image")
    private String image;

    public Author() {}

    public Author(String name, String penName, String biography, String image) {
        this.name = name;
        this.penName = penName;
        this.biography = biography;
        this.image = image;
    }

    public int getAuthorID() {
        return authorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPenName() {
        return penName;
    }

    public void setPenName(String penName) {
        this.penName = penName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
