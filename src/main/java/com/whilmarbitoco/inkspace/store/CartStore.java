package com.whilmarbitoco.inkspace.store;

import com.whilmarbitoco.inkspace.model.Cart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CartStore {

    private static ObservableList<Cart> cart;
    private static ObservableList<Cart> selected;

    public static ObservableList<Cart> cart() {
        if (cart == null) {
            cart = FXCollections.observableArrayList();
        }
        return cart;
    }

    public static ObservableList<Cart> selected() {
        if (selected == null) {
            selected = FXCollections.observableArrayList();
        }
        return selected;
    }

}

