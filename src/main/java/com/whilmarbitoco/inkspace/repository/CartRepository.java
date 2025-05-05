package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.Cart;
import com.whilmarbitoco.inkspace.model.Order;

import java.util.List;

public class CartRepository extends BaseRepository<Cart> {
    public CartRepository() {
        super(Cart.class);
    }

    public List<Cart> getUserCart(int bookID) {
        return findWhere("UserID", "=", bookID);
    }

    public Cart getCart(int userID, int bookID, int editionID, int coverID) {
        List<Cart> res = rawWhere("UserID = ? AND BookID = ? AND EditionID = ? AND CoverID = ?", userID, bookID, editionID, coverID);

        return res.isEmpty() ? null : res.getFirst();
    }
}
