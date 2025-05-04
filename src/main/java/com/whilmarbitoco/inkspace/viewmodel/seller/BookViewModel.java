package com.whilmarbitoco.inkspace.viewmodel.seller;

import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.store.StoreStore;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookViewModel extends BaseViewModel {

    private final ObservableList<Book> books = FXCollections.observableArrayList();

    private final BookRepository bookRepository = new BookRepository();

    public BookViewModel() {
        fetch();
    }

    public void fetch() {
        books.clear();
        books.addAll(bookRepository.findWhere("StoreID", "=", StoreStore.getInstance().getStore().getStoreID()));
    }

    public ObservableList<Book> getBooks() {
        return books;
    }
}
