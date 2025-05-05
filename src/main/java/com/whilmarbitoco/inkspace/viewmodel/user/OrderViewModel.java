package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.Order;
import com.whilmarbitoco.inkspace.repository.OrderRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrderViewModel extends BaseViewModel {

    private final List<Order> orders = new ArrayList<>();
    private final OrderRepository orderRepository = new OrderRepository();

    public OrderViewModel() {
        fetch();
    }

    private void fetch() {
        orders.clear();
        orders.addAll(orderRepository.getUserBought(currentUser.getUserID()));
    }

    public List<Order> getOrders() {
        return orders;
    }
}
