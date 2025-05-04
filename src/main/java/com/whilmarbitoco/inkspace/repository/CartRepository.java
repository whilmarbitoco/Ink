package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.Cart;

import java.util.List;

public class CartRepository extends BaseRepository<Cart> {
    public CartRepository() {
        super(Cart.class);
    }

    public List<Cart> getUserCart(int bookID) {
        return findWhere("UserID", "=", bookID);
    }

}
