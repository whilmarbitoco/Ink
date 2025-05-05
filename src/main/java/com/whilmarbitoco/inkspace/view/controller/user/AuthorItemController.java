package com.whilmarbitoco.inkspace.view.controller.user;


import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorItemController {

    public ImageView profileImage;
    public Label authorLabel;
    public Label biographyLabel;

    private Author author;

    public void setAuthor(Author author) {
        this.author = author;

        profileImage.setImage(ImageHelper.load(author.getImage()));
        authorLabel.setText(author.getName());
        biographyLabel.setText(author.getBiography());
    }

    public void actionClicked(MouseEvent mouseEvent) {
        try {
            Stage child = ViewHandler.primaryStage();

            FXMLLoader loader = ViewHandler.getLoader("user/SpecificAuthorView");
            Parent parent = loader.load();

            SpecificAuthorController controller = loader.getController();
            controller.setAuthor(author);
            Scene scene = new Scene(parent);
            child.setScene(scene);
            child.setResizable(false);
            child.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
