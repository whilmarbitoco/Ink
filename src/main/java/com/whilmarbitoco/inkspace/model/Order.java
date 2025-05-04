package com.whilmarbitoco.inkspace.model;


import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;
import com.whilmarbitoco.inkspace.utils.OrderStatus;

import java.sql.Date;

@Table(name = "`Order`")
public class Order {

    @Primary
    @Column(name = "OrderItemID")
    private int whhhh;

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

    @Column(name = "CreatedAt")
    private Date created;

    @Column(name = "Status")
    private String status;

    public Order() {}

    public Order(int userID, int bookID, int quantity, int editionID, int coverID) {
        this.userID = userID;
        this.bookID = bookID;
        this.quantity = quantity;
        this.editionID = editionID;
        this.coverID = coverID;
        this.created = new Date(System.currentTimeMillis());
        this.status = OrderStatus.Pending.name();
    }

    public int getOrderID() {
        return whhhh;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
