package com.whilmarbitoco.inkspace.viewmodel.seller.create;

import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.model.Cover;
import com.whilmarbitoco.inkspace.repository.AuthorRepository;
import com.whilmarbitoco.inkspace.repository.CoverRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookCoverViewModel extends BaseViewModel {

    private final ObservableList<Author> authors = FXCollections.observableArrayList();
    private final ObservableList<Cover> covers = FXCollections.observableArrayList();

    private final ObservableList<Author> selectedAuthors = FXCollections.observableArrayList();
    private final ObservableList<Cover> selectedCovers = FXCollections.observableArrayList();

    private final AuthorRepository authorRepository = new AuthorRepository();
    private final CoverRepository coverRepository = new CoverRepository();

    public BookCoverViewModel() {
        fetch();
    }

    public void fetch() {
        authors.clear();
        covers.clear();
        authors.addAll(authorRepository.findAll());
        covers.addAll(coverRepository.findAll());
    }

    public void changeView() {
        if (selectedAuthors.isEmpty() || selectedCovers.isEmpty()) {
            error.setValue("Please fill all fields");
            return;
        }
        switchTo("seller/create/BookEditionView");
    }

    public ObservableList<Author> getAuthors() {
        return authors;
    }

    public ObservableList<Cover> getCovers() {
        return covers;
    }

    public ObservableList<Author> getSelectedAuthors() {
        return selectedAuthors;
    }

    public ObservableList<Cover> getSelectedCovers() {
        return selectedCovers;
    }
}

