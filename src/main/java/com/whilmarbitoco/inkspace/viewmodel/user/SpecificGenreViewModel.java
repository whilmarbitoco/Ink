package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.model.Genre;
import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class SpecificGenreViewModel extends BaseViewModel  {

    private Genre genre;
    private final List<Book> books = new ArrayList<>();
    private final BookRepository bookRepository = new BookRepository();

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
        fetch();
    }

    private void fetch() {
        books.addAll(bookRepository.getBookByGenre(genre.getGenreID()));
    }

    public List<Book> getBooks() {
        return books;
    }
}
