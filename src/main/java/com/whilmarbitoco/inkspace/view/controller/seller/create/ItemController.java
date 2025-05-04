package com.whilmarbitoco.inkspace.view.controller.seller.create;

import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.model.Cover;
import com.whilmarbitoco.inkspace.model.Genre;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class ItemController {

    public Label text;

    private Author author;
    private Cover cover;
    private Genre genre;

    private DelAuthor deleteAuthor;
    private DelCover deleteCover;
    private DelGenre deleteGenre;

    public void setAuthor(Author author) {
        this.author = author;
        text.setText(author.getName());
    }

    public void setCover(Cover cover) {
        this.cover = cover;
        text.setText(cover.getCover());
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
        text.setText(genre.getGenre());
    }

    protected void setListener(DelAuthor del, DelCover delc) {
        deleteAuthor = del;
        deleteCover = delc;
    }

    protected void setGenreListener(DelGenre del) {
        deleteGenre = del;
    }

    public void deleteAction(ActionEvent actionEvent) {
        if (author != null) {
            deleteAuthor.deleteAuthor(author);
        }

        if (cover != null) {
            deleteCover.deleteCover(cover);
        }

        if (genre != null) {
            deleteGenre.deleteGenre(genre);
        }
    }
}
