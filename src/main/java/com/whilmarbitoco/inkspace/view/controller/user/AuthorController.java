package com.whilmarbitoco.inkspace.view.controller.user;

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

public class AuthorController extends BaseController  {


    public Label usernameLabel;
    public TextField searchField;
    public GridPane gridOne;
    public GridPane gridTwo;

    private final AuthorViewModel viewModel = new AuthorViewModel();

    public void initialize() {
        Platform.runLater(() -> {usernameLabel.requestFocus();});
        initDate();
        bindView();
        setViewModel(viewModel);
        usernameLabel.setText(viewModel.getCurrentUser().getFirstName() + " " + viewModel.getCurrentUser().getLastName());

        PauseTransition pause = new PauseTransition(Duration.millis(300));

//        debouncing
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            pause.setOnFinished(event -> {
                System.out.println(newText);
            });
            pause.playFromStart();
        });
    }

    private void initDate() {
        gridOne.getColumnConstraints().clear();
        gridOne.getRowConstraints().clear();
        gridTwo.getColumnConstraints().clear();
        gridTwo.getRowConstraints().clear();

        try {

            for (int i = 0; i < 4; i++) {

                FXMLLoader loader = ViewHandler.getLoader("user/AuthorItem");
                VBox pane  = loader.load();
                gridOne.add(pane, i, 0);
            }

            int row = 0;
            int col = 0;

            for (int i = 0; i < 10; i++) {
                if (col == 4) {
                    col = 0;
                    row++;
                }
                FXMLLoader loader = ViewHandler.getLoader("user/AuthorItem");
                VBox pane  = loader.load();
                gridTwo.add(pane, col++, row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void bindView() {
    }

}
