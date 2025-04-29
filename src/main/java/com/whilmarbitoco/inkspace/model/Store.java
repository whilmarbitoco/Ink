package com.whilmarbitoco.inkspace.model;

import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

@Table(name = "Store")
public class Store {

    @Primary
    @Column(name = "StoreID")
    private int storeID;

    @Column(name = "UserID")
    private int userID;

    @Column(name = "StoreName")
    private String storeName;

    public Store() {}

    public Store(int userID, String storeName) {
        this.userID = userID;
        this.storeName = storeName;
    }

    public int getStoreID() {
        return storeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
