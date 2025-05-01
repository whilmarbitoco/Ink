package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.repository.AuthorRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class AuthorViewModel extends BaseViewModel {

    private List<Author> authors = new ArrayList<>();

    private AuthorRepository authorRepository = new AuthorRepository();

    public AuthorViewModel() {
        fetch();
    }

    public void fetch() {
        authors.clear();
        authors.addAll(authorRepository.findAll());
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
