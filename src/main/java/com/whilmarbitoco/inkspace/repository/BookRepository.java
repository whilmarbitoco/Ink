package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.*;

import java.util.List;

public class BookRepository extends BaseRepository<Book> {

    private final JunctionRepository<BookAuthor> bookAuthorRepository = new JunctionRepository<>(BookAuthor.class);;
    private final JunctionRepository<BookCover> bookCoverRepository = new JunctionRepository<>(BookCover.class);
    private final JunctionRepository<BookGenre> bookGenreRepository = new JunctionRepository<>(BookGenre.class);
    private final EditionRepository editionRepository = new EditionRepository();
    private final BookDetailRepository bookDetailRepository = new BookDetailRepository();
    private final CoverRepository coverRepository = new CoverRepository();

    public BookRepository() {
        super(Book.class);
    }

    public void addAuthors(List<BookAuthor> authors) {
        for (BookAuthor author : authors) {
            bookAuthorRepository.create(author);
        }
    }

    public void addCovers(List<BookCover> covers) {
        for (BookCover cover : covers) {
            bookCoverRepository.create(cover);
        }
    }

    public void addGenres(List<BookGenre> genres) {
        for (BookGenre genre : genres) {
            bookGenreRepository.create(genre);
        }
    }

    public void addDetail(BookDetail detail) {
        bookDetailRepository.create(detail);
    }

    public BookDetail getDetail(int bookID) {
        return bookDetailRepository.findWhere("BookID", "=", bookID).getFirst();
    }

    public void deleteBook(int bookID) {
        bookAuthorRepository.deleteWhere("BookID = ?" , bookID);
        bookCoverRepository.deleteWhere("BookID = ?" , bookID);
        bookGenreRepository.deleteWhere("BookID = ?" , bookID);
        editionRepository.deleteWhere("BookID = ?", bookID);
        bookDetailRepository.deleteWhere("BookID = ?", bookID);
        delete(bookID);
    }

    public List<Edition> getEdition(int bookID) {
        return editionRepository.findWhere("BookID", "=", bookID);
    }

    public List<Cover> getCover(int bookID) {
        return  bookCoverRepository.findWhere("BookID", "=", bookID).stream().map(e -> {
            return coverRepository.findWhere("CoverTypeID", "=", e.getCoverID()).getFirst();
        }).toList();

    }
}
