package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.BookViewModel;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class StoreController extends BaseController {


    public Label usernameLabel;
    public TextField searchField;
    public GridPane gridOne;
    public Button createStoreBtn;
    public Button myStoreBtn;

    private final BookViewModel viewModel = new BookViewModel();


    public void initialize() {
        Platform.runLater(() -> {
            usernameLabel.requestFocus();
        });

        initData();
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

        if (viewModel.getCurrentUser().getRoleID() > 2) {
            createStoreBtn.setManaged(false);
            createStoreBtn.setVisible(false);
            myStoreBtn.setManaged(true);
            myStoreBtn.setVisible(true);
        }
    }

    private void initData() {

    }

    protected void bindView() {
    }

    public void createStore(ActionEvent actionEvent) {
        ViewHandler.openChildView("user/CreateStoreView");
    }

    public void myStore(ActionEvent actionEvent) {
    }
}
