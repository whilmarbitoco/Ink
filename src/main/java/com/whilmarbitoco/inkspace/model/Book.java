package com.whilmarbitoco.inkspace.model;


import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

import java.math.BigDecimal;

@Table(name = "Book")
public class Book {

    @Primary
    @Column(name = "BookID")
    private int bookID;

    @Column(name = "Title")
    private String title;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Image")
    private String image;

    @Column(name = "StoreID")
    private int storeID;

    public Book() {}

    public Book(String title, float price, int quantity, String image, int storeID) {
        this.title = title;
        this.price = BigDecimal.valueOf(price);
        this.quantity = quantity;
        this.image = image;
        this.storeID = storeID;
    }

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = BigDecimal.valueOf(price);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }
}
