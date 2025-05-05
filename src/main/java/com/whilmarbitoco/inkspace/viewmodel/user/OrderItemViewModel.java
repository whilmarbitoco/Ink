package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.model.BookDetail;
import com.whilmarbitoco.inkspace.model.Order;
import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;

public class OrderItemViewModel extends BaseViewModel {

    private Order order;
    private Book book;
    private BookDetail detail;

    private final BookRepository bookRepository = new BookRepository();

    public void setOrder(Order order) {
        this.order = order;
        bookRepository.findByID(order.getBookID()).ifPresent(e -> {
            book = e;
            detail = bookRepository.getDetail(order.getBookID());;
        });


    }

    public Order getOrder() {
        return order;
    }

    public Book getBook() {
        return book;
    }

    public BookDetail getDetail() {
        return detail;
    }
}
