package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.CartViewModel;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CartController extends BaseController {


    public TextField searchField;
    public Label usernameLabel;
    public GridPane grid;

    private final CartViewModel viewModel = new CartViewModel();

    public void initialize() {
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

    protected void bindView() {

    }
}
