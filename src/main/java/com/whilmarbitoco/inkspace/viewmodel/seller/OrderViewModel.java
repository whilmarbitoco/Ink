package com.whilmarbitoco.inkspace.viewmodel.seller;

import com.whilmarbitoco.inkspace.model.Order;
import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.repository.OrderRepository;
import com.whilmarbitoco.inkspace.store.StoreStore;
import com.whilmarbitoco.inkspace.utils.Tmp;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class OrderViewModel extends BaseViewModel {

    private final ObservableList<Tmp> orders = FXCollections.observableArrayList();

    private final OrderRepository orderRepository = new OrderRepository();
    private final BookRepository bookRepository = new BookRepository();


    public OrderViewModel() {
        fetch();
    }

    public void fetch() {
        orders.clear();
        List<Order> orderList = orderRepository.getStoreOrder(StoreStore.getInstance().getStore().getStoreID());

        for (Order o : orderList) {
            bookRepository.findByID(o.getBookID()).ifPresent(b -> {
                orders.add(new Tmp(o.getOrderID(), b.getTitle(), b.getPrice().floatValue(), o.getQuantity(), o.getStatus()));
            });
        }
    }

    public ObservableList<Tmp> getOrders() {
        return orders;
    }


}
