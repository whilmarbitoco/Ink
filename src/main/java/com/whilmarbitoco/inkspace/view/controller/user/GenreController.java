package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.GenreViewModel;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class GenreController extends BaseController {


    public GridPane gridOne;

    private final GenreViewModel viewModel = new GenreViewModel();
    public TextField searchField;
    public Label usernameLabel;


    public void initialize() {
        Platform.runLater(() -> gridOne.requestFocus());
        setViewModel(viewModel);
        bindView();
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

    protected void bindView() {

    }

}
