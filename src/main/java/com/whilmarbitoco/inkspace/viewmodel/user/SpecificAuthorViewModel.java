package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class SpecificAuthorViewModel extends BaseViewModel {

    private final List<Book> books = new ArrayList<>();

    private final BookRepository bookRepository = new BookRepository();

    private Author author;

    public void fetch() {
        books.clear();
        books.addAll(bookRepository.getBookByAuthor(author.getAuthorID()));
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
        fetch();
    }

    public List<Book> getBooks() {
        return books;
    }
}
