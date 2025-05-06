package com.whilmarbitoco.inkspace.viewmodel.admin;

import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.repository.AuthorRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class AuthorViewModel extends BaseViewModel {

    private final ObservableList<Author> authors = FXCollections.observableArrayList();

    private final AuthorRepository authorRepository = new AuthorRepository();

    public AuthorViewModel() {
        fetch();
    }


    public void fetch() {
        authors.clear();
        authors.addAll(authorRepository.findAll());
    }

    public ObservableList<Author> getAuthors() {
        return authors;
    }
}
