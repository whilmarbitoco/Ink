package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.Author;

public class AuthorRepository extends BaseRepository<Author> {
    public AuthorRepository() {
        super(Author.class);
    }
}
