package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.Author;

import java.util.List;

public class AuthorRepository extends BaseRepository<Author> {
    public AuthorRepository() {
        super(Author.class);
    }

    public List<Author> search(String str) {
        return rawWhere("Name LIKE ? OR PenName LIKE ?", "%"+str+"%", "%"+str+"%");
    }
}
