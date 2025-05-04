package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.Cart;
import com.whilmarbitoco.inkspace.model.Order;
import com.whilmarbitoco.inkspace.repository.CartRepository;
import com.whilmarbitoco.inkspace.repository.OrderRepository;
import com.whilmarbitoco.inkspace.store.CartStore;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartViewModel extends BaseViewModel {

    private final List<Cart> cartList = new ArrayList<>();

    private final CartRepository cartRepository = new CartRepository();
    private final OrderRepository orderRepository = new OrderRepository();

    public CartViewModel() {
        fetch();
    }

    private void fetch() {
        cartList.clear();
        cartList.addAll(cartRepository.getUserCart(currentUser.getUserID()));
    }

    public void delete() {
        for (Cart c : CartStore.selected()) {
            cartRepository.delete(c.getCartID());
        }
        CartStore.selected().clear();
        CartStore.cart().clear();
        CartStore.cart().addAll(cartRepository.getUserCart(currentUser.getUserID()));
    }

    public void order() {
        for (Cart c : CartStore.selected()) {
            Order order = new Order(currentUser.getUserID(),c.getBookID(), c.getQuantity(), c.getEditionID(), c.getCoverID());
            orderRepository.create(order);
        }
        delete();
        message.setValue("Order Created");
    }

    public List<Cart> getCartList() {
        return cartList;
    }
}
