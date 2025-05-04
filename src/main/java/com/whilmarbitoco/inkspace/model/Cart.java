package com.whilmarbitoco.inkspace.model;


import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

@Table(name = "Cart")
public class Cart {

    @Primary
    @Column(name = "CartID")
    private int cartID;

    @Column(name = "UserID")
    private int userID;

    @Column(name = "BookID")
    private int bookID;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "EditionID")
    private int editionID;

    @Column(name = "CoverID")
    private int coverID;

    public Cart() {}

    public Cart(int userID, int bookID, int quantity, int editionID, int coverID) {
        this.userID = userID;
        this.bookID = bookID;
        this.quantity = quantity;
        this.editionID = editionID;
        this.coverID = coverID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getUserID() {
        return userID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getEditionID() {
        return editionID;
    }

    public void setEditionID(int editionID) {
        this.editionID = editionID;
    }

    public int getCoverID() {
        return coverID;
    }

    public void setCoverID(int coverID) {
        this.coverID = coverID;
    }
}
