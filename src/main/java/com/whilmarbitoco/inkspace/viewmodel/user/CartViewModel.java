package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.model.Cart;
import com.whilmarbitoco.inkspace.model.Order;
import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.repository.CartRepository;
import com.whilmarbitoco.inkspace.repository.OrderRepository;
import com.whilmarbitoco.inkspace.store.CartStore;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class CartViewModel extends BaseViewModel {

    private final List<Cart> cartList = new ArrayList<>();

    private final CartRepository cartRepository = new CartRepository();
    private final OrderRepository orderRepository = new OrderRepository();
    private final BookRepository bookRepository = new BookRepository();

    public CartViewModel() {
        fetch();
    }

    public void fetch() {
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
            Order order = new Order(currentUser.getUserID(), c.getBookID(), c.getQuantity(), c.getEditionID(), c.getCoverID());
            Optional<Book> b = bookRepository.findByID(c.getBookID());
            if (b.isEmpty()) return;
            if (c.getQuantity() > b.get().getQuantity()) {
                error.setValue("Invalid Quantity for " + b.get().getTitle());
                return;
            }
            b.get().setQuantity(b.get().getQuantity() - c.getQuantity());
            bookRepository.update(b.get());
            orderRepository.create(order);
        }
        delete();
        message.setValue("Order Created");
    }

    public String getTotal() {
        List<Cart> cart = CartStore.selected();
        if (cart.isEmpty()) return "0";

        double total = cart.stream().map(c -> {
            AtomicReference<Double> tmp = new AtomicReference<>((double) 0);
            bookRepository.findByID(c.getBookID()).ifPresent(b -> tmp.set(b.getPrice().doubleValue() * c.getQuantity()));
            return tmp.get();

        }).mapToDouble(Number::doubleValue).sum();

        return Double.toString(total);
    }


    public List<Cart> getCartList() {
        return cartList;
    }
}
