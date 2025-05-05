package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookViewModel extends BaseViewModel {

    private final List<Book> books = new ArrayList<>();

    private final BookRepository bookRepository = new BookRepository();

    public BookViewModel() {
        fetch();
    }

    public void search(String str) {
        books.clear();
        List<Book> bks = bookRepository.search(str);
        books.addAll(bks);
    }

    public void fetch() {
        books.clear();
        books.addAll(bookRepository.findAll());
    }

    public List<Book> getBooks() {
        return books;
    }
}
