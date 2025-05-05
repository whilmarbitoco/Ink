package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.Genre;
import com.whilmarbitoco.inkspace.repository.GenreRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class GenreViewModel extends BaseViewModel {

    private final List<Genre> genreList = new ArrayList<>();

    private final GenreRepository genreRepository = new GenreRepository();

    public GenreViewModel() {
        fetch();
    }

    private void fetch() {
        genreList.clear();
        genreList.addAll(genreRepository.findAll());
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void search(String str) {
        genreList.clear();
        genreList.addAll(genreRepository.search(str));
    }
}
