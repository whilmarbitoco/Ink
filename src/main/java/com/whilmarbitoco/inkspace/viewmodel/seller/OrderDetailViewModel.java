package com.whilmarbitoco.inkspace.viewmodel.seller;

import com.whilmarbitoco.inkspace.model.*;
import com.whilmarbitoco.inkspace.repository.*;
import com.whilmarbitoco.inkspace.utils.OrderStatus;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;

import java.util.List;
import java.util.Optional;

public class OrderDetailViewModel extends BaseViewModel {
    private Order order;
    private Book book;
    private User user;
    private Edition edition;
    private Cover cover;
    private List<Address> addresses;
    private BookDetail detail;

    private final OrderRepository orderRepository = new OrderRepository();
    private final BookRepository bookRepository = new BookRepository();
    private final UserRepository userRepository = new UserRepository();
    private final EditionRepository editionRepository = new EditionRepository();
    private final CoverRepository coverRepository = new CoverRepository();
    private final AddressRepository addressRepository = new AddressRepository();

    public void orderID(int orderID) {
        Optional<Order> o = orderRepository.findByID(orderID);
        if (o.isEmpty()) return;

        order = o.get();
        userRepository.findByID(o.get().getUserID()).ifPresent(e -> user = e);
        bookRepository.findByID(o.get().getBookID()).ifPresent(e -> book = e);
        editionRepository.findByID(o.get().getEditionID()).ifPresent(e -> edition = e);
        coverRepository.findByID(o.get().getCoverID()).ifPresent(e -> cover = e);
        addresses = addressRepository.getByUser(o.get().getUserID());
        detail = bookRepository.getDetail(o.get().getBookID());
    }

    public void sell() {
        order.setStatus(OrderStatus.Ready.name());
        orderRepository.update(order);
        message.setValue("Book Sold.");
    }

    public Order getOrder() {
        return order;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public Edition getEdition() {
        return edition;
    }

    public Cover getCover() {
        return cover;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public BookDetail getDetail() {
        return detail;
    }
}
