package com.whilmarbitoco.inkspace.viewmodel.admin;

import com.whilmarbitoco.inkspace.model.Genre;
import com.whilmarbitoco.inkspace.repository.GenreRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GenreViewModel extends BaseViewModel {

    private final ObservableList<Genre> genres = FXCollections.observableArrayList();

    private final GenreRepository genreRepository = new GenreRepository();

    public GenreViewModel() {
        fetch();
    }

    public void fetch() {
        genres.clear();
        genres.addAll(genreRepository.findAll());
    }

    public ObservableList<Genre> getGenres() {
        return genres;
    }
}
