package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.AuthorViewModel;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.List;

public class AuthorController extends BaseController  {


    public Label usernameLabel;
    public TextField searchField;
    public GridPane grid;

    private final AuthorViewModel viewModel = new AuthorViewModel();

    public void initialize() {
        Platform.runLater(() -> {usernameLabel.requestFocus();});
        initData();
        bindView();
        setViewModel(viewModel);
        usernameLabel.setText(viewModel.getCurrentUser().getFirstName() + " " + viewModel.getCurrentUser().getLastName());

        PauseTransition pause = new PauseTransition(Duration.millis(300));

//        debouncing
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            pause.setOnFinished(event -> {
                viewModel.search(newText);
                initData();
            });
            pause.playFromStart();
        });
    }

    private void initData() {
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();

        try {
            int row = 0;
            int col = 0;
            List<Author> allAuthors = viewModel.getAuthors();

            for (Author author : allAuthors) {
                if (col == 4) {
                    col = 0;
                    row++;
                }
                FXMLLoader loader = ViewHandler.getLoader("user/AuthorItem");
                VBox pane  = loader.load();
                AuthorItemController controller = loader.getController();
                controller.setAuthor(author);
                grid.add(pane, col++, row);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void bindView() {
    }

}
