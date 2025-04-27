package com.whilmarbitoco.inkspace.view.controller.auth;

import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.auth.SignupViewModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignupController extends BaseController {

    public TextField firstnameField;
    public TextField lastnameField;
    public TextField emailField;
    public TextField passwordField;
    public TextField passwordVisibleField;

    private final SignupViewModel viewModel = new SignupViewModel();

    private boolean showPass = true;

    public void initialize() {
        bindView();
        setViewModel(viewModel);
    }

    @Override
    protected void bindView() {
        firstnameField.textProperty().bindBidirectional(viewModel.firstnameProperty());
        lastnameField.textProperty().bindBidirectional(viewModel.lastnameProperty());
        emailField.textProperty().bindBidirectional(viewModel.emailProperty());
        passwordField.textProperty().bindBidirectional(viewModel.passwordProperty());
        passwordVisibleField.textProperty().bindBidirectional(viewModel.passwordProperty());
    }

    public void signup(MouseEvent mouseEvent) {
        viewModel.signup();
    }

    public void switchTo(MouseEvent mouseEvent) {
        viewModel.switchTo("auth/LoginView");
    }

    public void togglePassword(MouseEvent mouseEvent) {
        showPass = !showPass;
        if (showPass) {
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            passwordVisibleField.setVisible(false);
            passwordVisibleField.setManaged(false);
            return;
        }
        passwordField.setVisible(false);
        passwordField.setManaged(false);
        passwordVisibleField.setVisible(true);
        passwordVisibleField.setManaged(true);
    }

}
