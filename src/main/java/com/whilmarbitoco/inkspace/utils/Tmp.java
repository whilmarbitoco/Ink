package com.whilmarbitoco.inkspace.utils;
public class Tmp {
    public int tmpID;
    public String title;
    public float price;
    public int quantity;
    public String status;

    public Tmp(int tmpID, String title, float price, int quantity, String status) {
        this.tmpID = tmpID;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public int getTmpID() {
        return tmpID;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }
}