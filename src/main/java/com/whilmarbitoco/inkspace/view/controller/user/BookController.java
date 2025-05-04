package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.BookViewModel;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BookController extends BaseController {


    public Label usernameLabel;
    public TextField searchField;
    public GridPane gridTwo;

    private final BookViewModel viewModel = new BookViewModel();

    public void initialize() {
        Platform.runLater(() -> {
            usernameLabel.requestFocus();
        });
        initDate();
        bindView();
        setViewModel(viewModel);
        usernameLabel.setText(viewModel.getCurrentUser().getFirstName() + " " + viewModel.getCurrentUser().getLastName());

//        debouncing
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            pause.setOnFinished(event -> {
                System.out.println(newText);
            });
            pause.playFromStart();
        });
    }

    private void initDate() {
        gridTwo.getColumnConstraints().clear();
        gridTwo.getRowConstraints().clear();

        try {
            int row = 0;
            int col = 0;

            for (int i = 0; i < viewModel.getBooks().size(); i++) {
                if (col == 4) {
                    col = 0;
                    row++;
                }
                FXMLLoader loader = ViewHandler.getLoader("user/BookItem");
                VBox pane = loader.load();
                BookItemController controller = loader.getController();
                controller.setBook(viewModel.getBooks().get(i));
                gridTwo.add(pane, col++, row);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void bindView() {
    }
}
