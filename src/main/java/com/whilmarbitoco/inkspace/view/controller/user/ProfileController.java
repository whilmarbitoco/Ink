package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.ProfileViewModel;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ProfileController extends BaseController {


    public TextField firstnameField;
    public TextField lastnameField;
    public TextField emailField;
    public Label pathField;
    public Button updateBtn;
    public ImageView profileImage;

    private final ProfileViewModel viewModel = new ProfileViewModel();

    public void initialize() {
        bindView();
        setViewModel(viewModel);

        listen(viewModel.firstnameProperty());
        listen(viewModel.lastnameProperty());
        listen(viewModel.emailProperty());
        listen(viewModel.imageProperty());
    }

    protected void bindView() {
        firstnameField.textProperty().bindBidirectional(viewModel.firstnameProperty());
        lastnameField.textProperty().bindBidirectional(viewModel.lastnameProperty());
        emailField.textProperty().bindBidirectional(viewModel.emailProperty());
        pathField.textProperty().bindBidirectional(viewModel.imageProperty());
        profileImage.setImage(ImageHelper.load(viewModel.imageProperty().get()));
    }

    public void updateAction(ActionEvent actionEvent) {
        viewModel.update();
    }

    public void openFile(ActionEvent mouseEvent) {
        String path = fileChooser(pathField);

        if (path == null) {
            pathField.setText("No such file selected");
            return;
        }
        pathField.setText(path);
        viewModel.imageProperty().setValue(path);
    }

    private void listen(StringProperty property) {
        property.addListener((obs, prev, now) -> {
            if (now.equals(prev)) return;
            updateBtn.setManaged(true);
            updateBtn.setVisible(true);
        });
    }

    public void switchToAddress(ActionEvent actionEvent) {
        ViewHandler.openChildView("user/AddressView");
    }
}
