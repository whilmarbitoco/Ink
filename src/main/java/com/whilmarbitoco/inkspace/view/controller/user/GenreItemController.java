package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Genre;
import javafx.scene.control.Label;

public class GenreItemController {
    public Label genreField;

    private Genre genre;

    public void setGenre(Genre genre) {
        this.genre = genre;
        genreField.setText(genre.getGenre());
    }
}
