package com.whilmarbitoco.inkspace.view.controller.admin;

import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.model.Genre;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.admin.GenreViewModel;
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

public class GenreController extends BaseController {

    public TableView<Genre> tableView;
    public TableColumn<Genre, Integer> genreID;
    public TableColumn<Genre, String> genre;
    public TableColumn<Genre, String> description;

    private final GenreViewModel viewModel = new GenreViewModel();

    public void initialize() {
        bindView();
        setViewModel(viewModel);

        genreID.setCellValueFactory(new PropertyValueFactory<>("genreID"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
    }

    protected void bindView() {
        tableView.setItems(viewModel.getGenres());
    }

    public void openAuthor(ActionEvent actionEvent) {
        viewModel.switchTo("admin/AuthorView");
    }

    public void addAction(ActionEvent actionEvent) {
        try {
            Stage child = ViewHandler.childStage();

            FXMLLoader loader = ViewHandler.getLoader("admin/GenrePopupView");
            Parent parent = loader.load();
            GenrePopupController controller = loader.getController();
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
                Genre selected = tableView.getSelectionModel().getSelectedItem();
                if (selected == null) return;
                Stage child = ViewHandler.childStage();

                FXMLLoader loader = ViewHandler.getLoader("admin/GenrePopupView");
                Parent parent = loader.load();
                GenrePopupController controller = loader.getController();
                controller.setGenre(selected);

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
