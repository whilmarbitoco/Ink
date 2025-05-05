package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Genre;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GenreItemController {
    public Label genreField;

    private Genre genre;

    public void setGenre(Genre genre) {
        this.genre = genre;
        genreField.setText(genre.getGenre());
    }

    public void actionClick(MouseEvent mouseEvent) {
        try {
            Stage child = ViewHandler.primaryStage();

            FXMLLoader loader = ViewHandler.getLoader("user/SpecificGenreView");
            Parent parent = loader.load();

            SpecificGenreController controller = loader.getController();
            controller.setGenre(genre);
            Scene scene = new Scene(parent);
            child.setScene(scene);
            child.setResizable(false);
            child.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
