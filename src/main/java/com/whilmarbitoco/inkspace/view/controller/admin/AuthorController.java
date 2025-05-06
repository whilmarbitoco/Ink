package com.whilmarbitoco.inkspace.view.controller.admin;

import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.view.controller.seller.BookInformationController;
import com.whilmarbitoco.inkspace.viewmodel.admin.AuthorViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorController extends BaseController {

    public TableView<Author> tableView;
    public TableColumn<Author, Integer> authorID;
    public TableColumn<Author, String> authorName;
    public TableColumn<Author, String> authorPenName;
    public TableColumn<Author, String> authorBiography;

    private final AuthorViewModel viewModel = new AuthorViewModel();

    public void initialize() {
        bindView();
        setViewModel(viewModel);

        authorID.setCellValueFactory(new PropertyValueFactory<>("AuthorID"));
        authorName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        authorPenName.setCellValueFactory(new PropertyValueFactory<>("PenName"));
        authorBiography.setCellValueFactory(new PropertyValueFactory<>("Biography"));
    }

    protected void bindView() {
        tableView.setItems(viewModel.getAuthors());
    }

    public void openGenre(ActionEvent actionEvent) {
        viewModel.switchTo("admin/GenreView");
    }

    public void addAction(ActionEvent actionEvent) {
        try {
            Stage child = ViewHandler.childStage();
            FXMLLoader loader = ViewHandler.getLoader("admin/AuthorPopupView");
            Parent parent = loader.load();
            AuthorPopupController controller = loader.getController();
            controller.createMode();

            Scene scene = new Scene(parent);
            child.setScene(scene);
            child.setResizable(false);
            child.setOnHidden(ee -> viewModel.fetch());
            child.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void tableMouseAction(MouseEvent mouseEvent) {
        try {
            if (mouseEvent.getClickCount() == 2) {
                Author selected = tableView.getSelectionModel().getSelectedItem();
                if (selected == null) return;
                Stage child = ViewHandler.childStage();

                FXMLLoader loader = ViewHandler.getLoader("admin/AuthorPopupView");
                Parent parent = loader.load();
                AuthorPopupController controller = loader.getController();
                controller.setAuthor(selected);

                Scene scene = new Scene(parent);
                child.setScene(scene);
                child.setResizable(false);
                child.setOnHidden(ee -> viewModel.fetch());
                child.show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openCover(ActionEvent actionEvent) {
        viewModel.switchTo("admin/CoverView");
    }
}
