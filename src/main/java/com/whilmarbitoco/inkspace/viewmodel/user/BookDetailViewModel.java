package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.*;
import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.repository.CartRepository;
import com.whilmarbitoco.inkspace.repository.OrderRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class BookDetailViewModel extends BaseViewModel {

    private final ObservableList<Edition> editions = FXCollections.observableArrayList();
    private final ObservableList<Cover> covers = FXCollections.observableArrayList();
    private final IntegerProperty quantity = new SimpleIntegerProperty(0);
    private final FloatProperty total = new SimpleFloatProperty(0);

    private Cover selectedCover;
    private Edition selectedEdition;

    private final BookRepository bookRepository = new BookRepository();
    private final CartRepository cartRepository = new CartRepository();
    private final OrderRepository orderRepository = new OrderRepository();

    private Book book;
    private BookDetail detail;

    public void fetch() {
        detail = bookRepository.getDetail(book.getBookID());

        editions.addAll(bookRepository.getEdition(book.getBookID()));
        covers.addAll(bookRepository.getCover(book.getBookID()));
    }

    public void setBook(Book book) {
        this.book = book;
        fetch();
    }

    public void addCart() {
        if (selectedCover == null || selectedEdition == null) {
            error.setValue("Please select a cover and an edition");
            return;
        }

        if (quantity.get() < 1 || total.get() < 1) {
            error.setValue("Quantity cannot be zero");
            return;
        }

        Cart res = cartRepository.getCart(currentUser.getUserID(), book.getBookID(), selectedEdition.getEditionID(), selectedCover.getCoverID());
        if (res == null) {
            Cart cart = new Cart(currentUser.getUserID(), book.getBookID(), quantity.get(), selectedEdition.getEditionID(), selectedCover.getCoverID());
            cartRepository.create(cart);
            message.setValue("Book Added to Cart");
            return;
        }
        res.setQuantity(res.getQuantity() + quantity.get());
        cartRepository.update(res);
        message.setValue("Book Added to Cart.");

    }

    public void buy() {
        if (selectedCover == null || selectedEdition == null) {
            error.setValue("Please selected a cover and an edition");
            return;
        }

        if (quantity.get() < 1) {
            error.setValue("Quantity cannot be zero");
            return;
        }

        Order order = new Order(currentUser.getUserID(), book.getBookID(), quantity.get(), selectedEdition.getEditionID(), selectedCover.getCoverID());
        Optional<Book> b = bookRepository.findByID(book.getBookID());
        if (b.isEmpty()) return;
        if (quantity.get() > b.get().getQuantity()) {
            error.setValue("Invalid Quantity for " + b.get().getTitle());
            return;
        }
        b.get().setQuantity(b.get().getQuantity() - quantity.get());
        bookRepository.update(b.get());
        orderRepository.create(order);

        message.setValue("Book Bought. Please check your orders.");
    }

    public Book getBook() {
        return book;
    }

    public BookDetail getDetail() {
        return detail;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public float getTotal() {
        return total.get();
    }

    public FloatProperty totalProperty() {
        return total;
    }

    public ObservableList<Edition> getEditions() {
        return editions;
    }

    public ObservableList<Cover> getCovers() {
        return covers;
    }

    public void setSelectedCover(Cover selectedCover) {
        this.selectedCover = selectedCover;
    }

    public void setSelectedEdition(Edition selectedEdition) {
        this.selectedEdition = selectedEdition;
    }
}
