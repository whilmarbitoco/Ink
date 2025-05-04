package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.Genre;

public class GenreRepository extends BaseRepository<Genre> {
    public GenreRepository() {
        super(Genre.class);
    }
}
