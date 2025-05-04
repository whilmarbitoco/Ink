package com.whilmarbitoco.inkspace.factory;

import com.whilmarbitoco.inkspace.viewmodel.seller.create.BookCoverViewModel;
import com.whilmarbitoco.inkspace.viewmodel.seller.create.BookEditionViewModel;
import com.whilmarbitoco.inkspace.viewmodel.seller.create.BookInfoViewModel;

public class CreateBookFactory {

    private static BookInfoViewModel bookInfo;
    private static BookCoverViewModel bookCover;
    private static BookEditionViewModel bookEdition;

    public static BookInfoViewModel createBookInfoVM() {
        if (bookInfo == null) {
            bookInfo = new BookInfoViewModel();
        }
        return bookInfo;
    }

    public static BookCoverViewModel createBookCoverVM() {
        if (bookCover == null) {
            bookCover = new BookCoverViewModel();
        }
        return bookCover;
    }

    public static BookEditionViewModel createBookEditionVM() {
        if (bookEdition == null) {
            bookEdition = new BookEditionViewModel();
        }
        return bookEdition;
    }

    public static void destroyAll() {
        bookInfo = null;
        bookCover = null;
        bookEdition = null;
    }
}
