package com.whilmarbitoco.inkspace.viewmodel.seller.create;

import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.model.BookDetail;
import com.whilmarbitoco.inkspace.repository.BookDetailRepository;
import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.store.StoreStore;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.time.LocalDate;

public class BookInfoViewModel extends BaseViewModel {

    private final StringProperty title = new SimpleStringProperty("");
    private final StringProperty price = new SimpleStringProperty("");
    private final StringProperty quantity = new SimpleStringProperty("");
    private final StringProperty filepath = new SimpleStringProperty("No such file selected");
    private final ObjectProperty<LocalDate> published = new SimpleObjectProperty<>(LocalDate.now());
    private final ObjectProperty<Date> date = new SimpleObjectProperty<>();
    private final StringProperty publisher = new SimpleStringProperty("");
    private final StringProperty description = new SimpleStringProperty("");

    public void changeView() {
        if (getTitle().isEmpty() || getPrice().isEmpty() || getQuantity().isEmpty()
        || getFilepath().isEmpty() || getPublisher().isEmpty() || getDescription().isEmpty()) {
            error.setValue("Please fill all fields.");
            return;
        }

        try {
            Float.parseFloat(price.get());
            Integer.parseInt(quantity.get());

        } catch (NumberFormatException e) {
            error.setValue("Price and Quantity must be numbers");
            return;
        }

        switchTo("seller/create/BookCoverView");
    }

    public String getTitle() {
        return title.get();
    }

    public Book createBook() {
        Book book = new Book();
        book.setTitle(title.get());
        book.setPrice(Float.parseFloat(price.get()));
        book.setQuantity(Integer.parseInt(quantity.get()));
        book.setImage(filepath.get());
        book.setStoreID(StoreStore.getInstance().getStore().getStoreID());
        return book;
    }

    public BookDetail createDetail(int id) {
        return new BookDetail(description.get(), date.get(), publisher.get(), id);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public String getQuantity() {
        return quantity.get();
    }

    public StringProperty quantityProperty() {
        return quantity;
    }

    public String getFilepath() {
        return filepath.get();
    }

    public StringProperty filepathProperty() {
        return filepath;
    }

    public LocalDate getPublished() {
        return published.get();
    }

    public ObjectProperty<LocalDate> publishedProperty() {
        return published;
    }

    public String getPublisher() {
        return publisher.get();
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public Date getDate() {
        return date.get();
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }
}
