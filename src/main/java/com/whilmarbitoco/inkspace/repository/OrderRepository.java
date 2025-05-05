package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.Order;

import java.util.List;
import java.util.Optional;

public class OrderRepository extends BaseRepository<Order> {
    public OrderRepository() {
        super(Order.class);
    }

    public List<Order> getStoreOrder(int storeID) {
        return Raw("SELECT o.* FROM `order` o JOIN Book b ON o.bookID = b.bookID WHERE o.Status = 'Pending' AND b.storeID = ?", storeID);
    }

    public List<Order> getStoreSold(int storeID) {
        return Raw("SELECT o.* FROM `order` o JOIN Book b ON o.bookID = b.bookID WHERE o.Status = 'Ready' AND b.storeID = ?", storeID);
    }

    public List<Order> getUserBought(int userID) {
        return rawWhere("UserID = ? AND `status` = 'Ready'", userID);
    }

}
