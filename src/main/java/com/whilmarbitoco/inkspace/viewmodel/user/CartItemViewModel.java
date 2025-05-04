package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.model.Cart;
import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.repository.CartRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Optional;

public class CartItemViewModel extends BaseViewModel {

    private final IntegerProperty quantity = new SimpleIntegerProperty();

    private final CartRepository cartRepository = new CartRepository();
    private final BookRepository bookRepository = new BookRepository();

    private Cart cart;
    private Book book;

    public void setCart(Cart cart) {
        this.cart = cart;
        quantity.setValue(cart.getQuantity());
        bookRepository.findByID(cart.getBookID()).ifPresent(value -> book = value);
    }

    public Cart getCart() {
        return cart;
    }

    public Book getBook() {
        return book;
    }

    public void update() {
        if (cart == null) return;
        cartRepository.update(cart);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }
}
