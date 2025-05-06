package com.whilmarbitoco.inkspace.view.controller.admin;

import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.repository.AuthorRepository;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.admin.AuthorViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AuthorPopupController extends BaseController {

    public ImageView bookImg;
    public TextField nameField;
    public TextField penNameField;
    public TextArea biographyArea;
    public Button updateBtn;
    public Button createBtn;
    public Button enableEdit;
    public Button enableDelete;

    private String filepath = "";
    private Author author;

    private final AuthorRepository authorRepository = new AuthorRepository();
    private final AuthorViewModel viewModel = new AuthorViewModel();

    public void initialize() {
        setViewModel(viewModel);
    }

    protected void bindView() {
        System.out.println(author.getImage());
        bookImg.setImage(ImageHelper.load(author.getImage()));
        nameField.setText(author.getName());
        penNameField.setText(author.getPenName());
        biographyArea.setText(author.getBiography());
    }

    public void createMode() {
        enableEdit(null);
        updateBtn.setVisible(false);
        updateBtn.setManaged(false);
        createBtn.setVisible(true);
        createBtn.setManaged(true);
        enableEdit.setVisible(false);
        enableDelete.setVisible(false);
    }

    public void setAuthor(Author author) {
            this.author = author;
            bindView();
    }

    public void enableEdit(ActionEvent actionEvent) {
        nameField.setEditable(true);
        penNameField.setEditable(true);
        biographyArea.setEditable(true);
    }

    public void deleteAction(ActionEvent actionEvent) {
        boolean res = showConfirmation("Confirm Action", "Do you really want to delete this author");
        if (!res) return;
        authorRepository.delete(author.getAuthorID());
    }

    public void changeImage(MouseEvent mouseEvent) {
        if (!nameField.isEditable()) return;

        String file = fileChooser(nameField);

        if (file == null) return;
        filepath = file;
        bookImg.setImage(ImageHelper.load(file));
    }

    public void editAction(ActionEvent actionEvent) {
        if (nameField.getText().isEmpty() || biographyArea.getText().isEmpty()) {
            showError("Error", "Please fill all fields");
            return;
        }
        author.setName(nameField.getText());
        author.setPenName(author.getPenName());
        author.setBiography(biographyArea.getText());
        if (!filepath.isEmpty()) {
            author.setImage(filepath);
        }

        showConfirmation("Success", "Author edited");
        close(nameField);
    }

    public void createAction(ActionEvent actionEvent) {
        if (nameField.getText().isEmpty() || biographyArea.getText().isEmpty()
        || filepath.isEmpty()) {
            showError("Error", "Please fill all fields");
            return;
        }

        authorRepository.create(new Author(nameField.getText(), penNameField.getText(), biographyArea.getText(), filepath));
        showConfirmation("Success", "Author created");
        close(nameField);
    }
}
