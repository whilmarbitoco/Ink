package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class BookItemController {

    public ImageView image;
    public Label titleField;
    public Label priceField;

    private Book book;

    public void setBook(Book book) {
        this.book = book;

        image.setImage(ImageHelper.load(book.getImage()));
        titleField.setText(book.getTitle());
        priceField.setText(book.getPrice().toString());
    }

    public void action(ActionEvent mouseEvent) {
        try {
            Stage child = new Stage();

            FXMLLoader loader = ViewHandler.getLoader("user/BookDetailView");
            Parent parent = loader.load();

            BookDetailController controller = loader.getController();
            controller.setBook(book);
            Scene scene = new Scene(parent);
            child.setScene(scene);
            child.setResizable(false);
            child.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
