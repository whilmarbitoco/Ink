package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.Review;

import java.util.List;

public class ReviewRepository extends BaseRepository<Review> {
    public ReviewRepository() {
        super(Review.class);
    }

    public List<Review> getByBook(int bookID) {
        return findWhere("BookID", "=", bookID);
    }
}
