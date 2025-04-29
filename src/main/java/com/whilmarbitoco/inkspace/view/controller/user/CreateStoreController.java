package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.CreateStoreViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateStoreController extends BaseController {

    public Label label;
    public TextField storeField;

    private final CreateStoreViewModel viewModel = new CreateStoreViewModel();

    public void initialize() {
        Platform.runLater(() -> label.requestFocus());
        bindView();
        setViewModel(viewModel);

        viewModel.messageProperty().addListener((o,p, n) -> {
            close(label);
        });
    }

    protected void bindView() {
        storeField.textProperty().bindBidirectional(viewModel.storeNameProperty());
    }

    public void createAction(ActionEvent actionEvent) {
        viewModel.create();
    }

    public void cancelAction(ActionEvent actionEvent) {
        close(label);
    }
}
