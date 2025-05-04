package com.whilmarbitoco.inkspace.view.controller.seller.create;

import com.whilmarbitoco.inkspace.factory.CreateBookFactory;
import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.model.Edition;
import com.whilmarbitoco.inkspace.model.Genre;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.seller.create.BookEditionViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

import java.io.IOException;

public class BookEditionController extends BaseController implements DelGenre, DelEdition {

    public GridPane gridOne;
    public GridPane gridTwo;
    public ComboBox<Genre> genreBox;
    public TextField isbnField;
    public TextField editionField;

    private final BookEditionViewModel viewModel = CreateBookFactory.createBookEditionVM();

    public void initialize() {
        bindView();
        setViewModel(viewModel);
        initGridTwo();
        initGridOne();

        genreBox.getEditor().textProperty().addListener((o, p, n) -> {
            if (n == null) return;

            Genre genre = genreBox.getSelectionModel().getSelectedItem();
            if (genre != null && genre.toString().equalsIgnoreCase(n)) return;

            pause.setOnFinished(event -> {
                ObservableList<Genre> filtered = FXCollections.observableArrayList();
                for (Genre g : viewModel.getGenre()) {
                    if (g.toString().toLowerCase().contains(n.toLowerCase())) {
                        filtered.add(g);
                    }
                }
                genreBox.setItems(filtered);
                genreBox.show();
            });

            pause.playFromStart();
        });

        genreBox.setConverter(new StringConverter<Genre>() {
            @Override
            public String toString(Genre genre) {
                return genre != null ? genre.getGenre() : "";
            }

            @Override
            public Genre fromString(String s) {
                for (Genre genre : genreBox.getItems()) {
                    if (genre.getGenre().equalsIgnoreCase(s.trim())) {
                        return genre;
                    }
                }
                return null;
            }
        });

        viewModel.getSelectedGenre().addListener((ListChangeListener<? super Genre>) change -> initGridOne());
        viewModel.getSelectedEdition().addListener((ListChangeListener<? super Edition>) change -> initGridTwo());
    }

    private void initGridTwo() {
        gridTwo.getColumnConstraints().clear();;
        gridTwo.getRowConstraints().clear();;
        gridTwo.getChildren().clear();

        try {
            for (int i = 0; i < viewModel.getSelectedEdition().size(); i++) {
                FXMLLoader loader = ViewHandler.getLoader("seller/create/ISBNView");;
                HBox pane = loader.load();
                ISBNController controller = loader.getController();
                controller.setListener(this);
                controller.setEdition(viewModel.getSelectedEdition().get(i));
                gridTwo.add(pane, 0, i);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initGridOne() {
        gridOne.getRowConstraints().clear();;
        gridOne.getColumnConstraints().clear();;
        gridOne.getChildren().clear();

        try {
            for (int i = 0; i < viewModel.getSelectedGenre().size(); i++) {
                FXMLLoader loader = ViewHandler.getLoader("seller/create/ItemView");;
                HBox pane = loader.load();
                ItemController controller = loader.getController();
                controller.setGenre(viewModel.getSelectedGenre().get(i));
                controller.setGenreListener(this);
                gridOne.add(pane, 0, i);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void bindView() {
        genreBox.setItems(viewModel.getGenre());
        isbnField.textProperty().bindBidirectional(viewModel.isbnProperty());
        editionField.textProperty().bindBidirectional(viewModel.editionProperty());
    }

    public void cancelAction(ActionEvent actionEvent) {
        ViewHandler.openView("seller/create/BookCoverView");
    }

    public void nextAction(ActionEvent actionEvent) {
        viewModel.create();
    }

    @Override
    public void deleteGenre(Genre genre) {
        viewModel.getSelectedGenre().remove(genre);
    }

    public void genreAction(ActionEvent actionEvent) {
        Genre genre = genreBox.getSelectionModel().getSelectedItem();
        if (genre != null && genreBox.isFocused()) {
            viewModel.getSelectedGenre().add(genre);

            genreBox.getEditor().commitValue();
            genreBox.hide();

            Platform.runLater(() -> gridOne.requestFocus());
        }
    }

    public void addEdition(ActionEvent actionEvent) {
        viewModel.addEdition();
    }

    @Override
    public void deleteEdition(Edition edition) {
        viewModel.getSelectedEdition().remove(edition);
    }
}
