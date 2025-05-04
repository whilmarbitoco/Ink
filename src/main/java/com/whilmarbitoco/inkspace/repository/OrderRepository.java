package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.Order;

import java.util.List;

public class OrderRepository extends BaseRepository<Order> {
    public OrderRepository() {
        super(Order.class);
    }

    public List<Order> getStoreOrder(int storeID) {
        return Raw("SELECT o.* FROM `order` o JOIN Book b ON o.bookID = b.bookID WHERE o.Status = 'Pending' AND b.storeID = ?", storeID);
    }
}
