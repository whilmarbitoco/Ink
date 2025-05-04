package com.whilmarbitoco.inkspace.viewmodel.seller.create;

import com.whilmarbitoco.inkspace.factory.CreateBookFactory;
import com.whilmarbitoco.inkspace.model.*;
import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.repository.EditionRepository;
import com.whilmarbitoco.inkspace.repository.GenreRepository;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.List;

public class BookEditionViewModel extends BaseViewModel {

    private final ObservableList<Genre> genre = FXCollections.observableArrayList();
    private final ObservableList<Genre> selectedGenre = FXCollections.observableArrayList();
    private final ObservableList<Edition> selectedEdition = FXCollections.observableArrayList();

    private final StringProperty isbn = new SimpleStringProperty("");
    private final StringProperty edition = new SimpleStringProperty("");

    private final BookRepository bookRepository = new BookRepository();
    private final GenreRepository genreRepository = new GenreRepository();
    private final EditionRepository editionRepository = new EditionRepository();

    private final BookInfoViewModel bookInfoVM = CreateBookFactory.createBookInfoVM();
    private final BookCoverViewModel bookCoverVM = CreateBookFactory.createBookCoverVM();

    public BookEditionViewModel() {
        fetch();
    }

    public void fetch() {
        genre.addAll(genreRepository.findAll());
    }

    public void addEdition() {
        if (isbn.get().isEmpty() || edition.get().isEmpty()) {
            error.setValue("ISBN and Edition must be filled");
            return;
        }

        if (isbn.get().length() < 8) {
            error.setValue("ISBN must be 8 characters long");
            return;
        }

        Edition tmp = new Edition(edition.get(), isbn.get(), 0);
        selectedEdition.add(tmp);
    }

    public void create() {

        if (selectedGenre.isEmpty() || selectedEdition.isEmpty()) {
            error.setValue("Please fill out genre and edition");
            return;
        }

        Book book = bookInfoVM.createBook();

        try {
            String path = ImageHelper.save(book.getImage());
            book.setImage(path);
            bookRepository.create(book);
        } catch (IOException e) {
            error.setValue("Error uploading file.");
            return;
        }


        int id = bookRepository.max();

        BookDetail detail = bookInfoVM.createDetail(id);
        bookRepository.addDetail(detail);


        List<BookAuthor> authors = bookCoverVM.getSelectedAuthors().stream().map(a -> {
            BookAuthor author = new BookAuthor();
            author.setBookID(id);
            author.setAuthorID(a.getAuthorID());
            return author;
        }).toList();

        bookRepository.addAuthors(authors);

        List<BookCover> covers = bookCoverVM.getSelectedCovers().stream().map(c -> {
            BookCover cover = new BookCover();
            cover.setBookID(id);
            cover.setCoverID(c.getCoverID());
            return cover;
        }).toList();

        bookRepository.addCovers(covers);

        List<BookGenre> genres = selectedGenre.stream().map(g -> {
            BookGenre tmp = new BookGenre();
            tmp.setBookID(id);
            tmp.setGenreID(g.getGenreID());
            return tmp;
        }).toList();

        bookRepository.addGenres(genres);

        for (Edition e : selectedEdition) {
            e.setBookID(id);
            editionRepository.create(e);
        }

        message.setValue("Book Created Successfully");
        switchTo("seller/BookView");
    }

    public ObservableList<Genre> getGenre() {
        return genre;
    }

    public ObservableList<Edition> getSelectedEdition() {
        return selectedEdition;
    }

    public ObservableList<Genre> getSelectedGenre() {
        return selectedGenre;
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public StringProperty editionProperty() {
        return edition;
    }
}
