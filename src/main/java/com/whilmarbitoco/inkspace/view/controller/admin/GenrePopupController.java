package com.whilmarbitoco.inkspace.view.controller.admin;

import com.whilmarbitoco.inkspace.model.Genre;
import com.whilmarbitoco.inkspace.repository.GenreRepository;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GenrePopupController extends BaseController {

    public Button enableEdit;
    public Button enableDelete;
    public TextField genreField;
    public TextArea descriptionArea;
    public Button updateBtn;
    public Button createBtn;

    private Genre genre;

    private final GenreRepository genreRepository = new GenreRepository();

    protected void bindView() {
        genreField.setText(genre.getGenre());
        descriptionArea.setText(genre.getDescription());
    }

    private void enableEdit() {
        genreField.setEditable(true);
        descriptionArea.setEditable(true);
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
        bindView();
    }

    public void createMode() {
        enableEdit();
        updateBtn.setVisible(false);
        updateBtn.setManaged(false);
        createBtn.setVisible(true);
        createBtn.setManaged(true);
    }

    public void enable(ActionEvent actionEvent) {
        enableEdit();
    }

    public void deleteAction(ActionEvent actionEvent) {
        boolean res = showConfirmation("Confirm", "Do you really want to delete this genre?");
        if (!res) return;
        genreRepository.delete(genre.getGenreID());
        showInformation("Success", "Genre Deleted.");
        close(genreField);
    }

    public void editAction(ActionEvent actionEvent) {
        if (genreField.getText().isEmpty() || descriptionArea.getText().isEmpty()) {
            showError("Error", "Please fill all fields");
            return;
        }

        genre.setGenre(genreField.getText());
        genre.setDescription(descriptionArea.getText());
        genreRepository.update(genre);
        showInformation("Success", "Genre Updated.");
        close(genreField);
    }

    public void createAction(ActionEvent actionEvent) {
        if (genreField.getText().isEmpty() || descriptionArea.getText().isEmpty()) {
            showError("Error", "Please fill all fields");
            return;
        }

        genreRepository.create(new Genre(genreField.getText(), descriptionArea.getText()));
        showInformation("Success", "Genre Created.");
        close(genreField);
    }
}
