package com.whilmarbitoco.inkspace.view.controller.seller.create;

import com.whilmarbitoco.inkspace.factory.CreateBookFactory;
import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.model.Cover;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.seller.create.BookCoverViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

import java.io.IOException;

public class BookCoverController extends BaseController implements DelAuthor, DelCover {

    public GridPane gridOne;
    public GridPane gridTwo;
    public ChoiceBox<Cover> coverBox;
    public ComboBox<Author> authorBox;


    private final BookCoverViewModel viewModel = CreateBookFactory.createBookCoverVM();

    public void initialize() {
        bindView();
        setViewModel(viewModel);
        initDataGridOne();
        initDataGridTwo();

        coverBox.getSelectionModel().selectedItemProperty().addListener((o, p, n) -> {
            if (p != n) {
                viewModel.getSelectedCovers().add(n);
            }
        });

        authorBox.getEditor().textProperty().addListener((obs, oldText, newText) -> {
            if (newText == null) return;

            Author selected = authorBox.getSelectionModel().getSelectedItem();
            if (selected != null && selected.toString().equals(newText)) {
                return;
            }

            pause.setOnFinished(event -> {
                ObservableList<Author> filtered = FXCollections.observableArrayList();
                for (Author item : viewModel.getAuthors()) {
                    if (item.toString().toLowerCase().contains(newText.toLowerCase())) {
                        filtered.add(item);
                    }
                }
                authorBox.setItems(filtered);
                authorBox.show();
            });

            pause.playFromStart();
        });


        authorBox.setConverter(new StringConverter<Author>() {
            @Override
            public String toString(Author author) {
                return author != null ? author.getName() : "";
            }

            @Override
            public Author fromString(String string) {
                for (Author a : authorBox.getItems()) {
                    if (a.getName().equalsIgnoreCase(string.trim())) {
                        return a;
                    }
                }
                return null;
            }
        });

        viewModel.getSelectedAuthors().addListener((ListChangeListener<? super Author>) observable -> initDataGridTwo());

        viewModel.getSelectedCovers().addListener((ListChangeListener<? super Cover>) observable -> initDataGridOne());
    }

    private void initDataGridOne() {
        gridOne.getRowConstraints().clear();
        gridOne.getColumnConstraints().clear();
        gridOne.getChildren().clear();

        try {
            for (int i = 0; i < viewModel.getSelectedCovers().size(); i++) {
                FXMLLoader loader = ViewHandler.getLoader("seller/create/ItemView");;
                HBox pane = loader.load();
                ItemController controller = loader.getController();
                controller.setCover(viewModel.getSelectedCovers().get(i));
                controller.setListener(this, this);
                gridOne.add(pane, 0, i);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initDataGridTwo() {
        gridTwo.getRowConstraints().clear();
        gridTwo.getColumnConstraints().clear();
        gridTwo.getChildren().clear();

        try {
            for (int i = 0; i < viewModel.getSelectedAuthors().size(); i++) {
                FXMLLoader loader = ViewHandler.getLoader("seller/create/ItemView");;
                HBox pane = loader.load();
                ItemController controller = loader.getController();
                controller.setAuthor(viewModel.getSelectedAuthors().get(i));
                controller.setListener(this, this);
                gridTwo.add(pane, 0, i);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void bindView() {
        coverBox.getItems().addAll(viewModel.getCovers());
        authorBox.setItems(viewModel.getAuthors());

    }

    public void cancelAction(ActionEvent actionEvent) {
        ViewHandler.openView("seller/create/BookInfoView");
    }

    public void nextAction(ActionEvent actionEvent) {
        viewModel.changeView();
    }

    public void authorAction(ActionEvent actionEvent) {
        Author selected = authorBox.getSelectionModel().getSelectedItem();
        if (selected != null && authorBox.isFocused()) {
            viewModel.getSelectedAuthors().add(selected);

            authorBox.getEditor().commitValue();
            authorBox.hide();

            Platform.runLater(() -> gridOne.requestFocus());
        }
    }

    @Override
    public void deleteAuthor(Author author) {
        viewModel.getSelectedAuthors().remove(author);
    }

    @Override
    public void deleteCover(Cover cover) {
        viewModel.getSelectedCovers().remove(cover);
    }
}
