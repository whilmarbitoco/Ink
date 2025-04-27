package com.whilmarbitoco.inkspace.view.controller;

import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

abstract public class BaseController {

    private BaseViewModel viewModel;

    protected void setViewModel(BaseViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.errorProperty().addListener((obs, prev, now) -> {
           if (now == null) return;
           showError("Something went wrong", now);
           viewModel.errorProperty().setValue(null);
        });

        viewModel.messageProperty().addListener((obs, prev, now) -> {
           if (now == null) return;
           showInformation("Success", now);
           viewModel.messageProperty().setValue(null);
        });
    }

    protected abstract void bindView();

    public void gotoProfile(MouseEvent mouseEvent) {
    }

    public void gotoBook(MouseEvent mouseEvent) {
        viewModel.switchTo("user/BookView");
    }

    public void gotoGenre(MouseEvent mouseEvent) {
        viewModel.switchTo("user/GenreView");
    }

    public void gotoAuthor(MouseEvent mouseEvent) {
        viewModel.switchTo("user/AuthorView");
    }

    public void gotoStore(MouseEvent mouseEvent) {
    }

    public void gotoCart(MouseEvent mouseEvent) {
    }

    public void logout() {
        boolean confirm = showConfirmation("Logout", "Do you really wanna logout?");

        if (confirm) {
            viewModel.logout();
        }
    }

    protected boolean showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }

    protected void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    protected void showInformation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
