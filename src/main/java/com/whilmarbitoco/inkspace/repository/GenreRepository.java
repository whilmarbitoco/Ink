package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.Genre;

import java.util.List;

public class GenreRepository extends BaseRepository<Genre> {
    public GenreRepository() {
        super(Genre.class);
    }

    public List<Genre> search(String str) {
        return findLike("GenreName", str);
    }
}
